/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.										*
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/
package org.xpande.logistica.model;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.acct.Doc;
import org.compiere.model.*;
import org.compiere.process.DocAction;
import org.compiere.process.DocOptions;
import org.compiere.process.DocumentEngine;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;
import org.xpande.comercial.model.MZReservaVta;
import org.xpande.comercial.model.MZReservaVtaLin;
import org.xpande.core.model.MZProductoUPC;
import org.xpande.core.utils.PriceListUtils;

/** Generated Model for Z_MassiveInv
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class MZMassiveInv extends X_Z_MassiveInv implements DocAction, DocOptions {

	/**
	 *
	 */
	private static final long serialVersionUID = 20210622L;

    /** Standard Constructor */
    public MZMassiveInv (Properties ctx, int Z_MassiveInv_ID, String trxName)
    {
      super (ctx, Z_MassiveInv_ID, trxName);
    }

    /** Load Constructor */
    public MZMassiveInv (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

	@Override
	public int customizeValidActions(String docStatus, Object processing, String orderType, String isSOTrx, int AD_Table_ID, String[] docAction, String[] options, int index) {

		int newIndex = 0;

		if ((docStatus.equalsIgnoreCase(STATUS_Drafted))
				|| (docStatus.equalsIgnoreCase(STATUS_Invalid))
				|| (docStatus.equalsIgnoreCase(STATUS_InProgress))){

			options[newIndex++] = DocumentEngine.ACTION_Complete;

		}
		else if (docStatus.equalsIgnoreCase(STATUS_Completed)){

			options[newIndex++] = DocumentEngine.ACTION_None;
			//options[newIndex++] = DocumentEngine.ACTION_ReActivate;
			//options[newIndex++] = DocumentEngine.ACTION_Void;
		}
		return newIndex;
	}

	/**
	 * 	Get Document Info
	 *	@return document info (untranslated)
	 */
	public String getDocumentInfo()
	{
		MDocType dt = MDocType.get(getCtx(), getC_DocType_ID());
		return dt.getName() + " " + getDocumentNo();
	}	//	getDocumentInfo

	/**
	 * 	Create PDF
	 *	@return File or null
	 */
	public File createPDF ()
	{
		try
		{
			File temp = File.createTempFile(get_TableName() + get_ID() +"_", ".pdf");
			return createPDF (temp);
		}
		catch (Exception e)
		{
			log.severe("Could not create PDF - " + e.getMessage());
		}
		return null;
	}	//	getPDF

	/**
	 * 	Create PDF file
	 *	@param file output file
	 *	@return file if success
	 */
	public File createPDF (File file)
	{
	//	ReportEngine re = ReportEngine.get (getCtx(), ReportEngine.INVOICE, getC_Invoice_ID());
	//	if (re == null)
			return null;
	//	return re.getPDF(file);
	}	//	createPDF

	
	/**************************************************************************
	 * 	Process document
	 *	@param processAction document action
	 *	@return true if performed
	 */
	public boolean processIt (String processAction)
	{
		m_processMsg = null;
		DocumentEngine engine = new DocumentEngine (this, getDocStatus());
		return engine.processIt (processAction, getDocAction());
	}	//	processIt
	
	/**	Process Message 			*/
	private String		m_processMsg = null;
	/**	Just Prepared Flag			*/
	private boolean		m_justPrepared = false;

	/**
	 * 	Unlock Document.
	 * 	@return true if success 
	 */
	public boolean unlockIt()
	{
		log.info("unlockIt - " + toString());
	//	setProcessing(false);
		return true;
	}	//	unlockIt
	
	/**
	 * 	Invalidate Document
	 * 	@return true if success 
	 */
	public boolean invalidateIt()
	{
		log.info("invalidateIt - " + toString());
		setDocAction(DOCACTION_Prepare);
		return true;
	}	//	invalidateIt
	
	/**
	 *	Prepare Document
	 * 	@return new status (In Progress or Invalid) 
	 */
	public String prepareIt()
	{
		log.info(toString());
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_PREPARE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;
		
		MDocType dt = MDocType.get(getCtx(), getC_DocType_ID());

		/*
		//	Std Period open?
		if (!MPeriod.isOpen(getCtx(), getDateDoc(), dt.getDocBaseType(), getAD_Org_ID()))
		{
			m_processMsg = "@PeriodClosed@";
			return DocAction.STATUS_Invalid;
		}
		*/

		//	Add up Amounts
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_PREPARE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;
		m_justPrepared = true;
		if (!DOCACTION_Complete.equals(getDocAction()))
			setDocAction(DOCACTION_Complete);
		return DocAction.STATUS_InProgress;
	}	//	prepareIt
	
	/**
	 * 	Approve Document
	 * 	@return true if success 
	 */
	public boolean  approveIt()
	{
		log.info("approveIt - " + toString());
		setIsApproved(true);
		return true;
	}	//	approveIt
	
	/**
	 * 	Reject Approval
	 * 	@return true if success 
	 */
	public boolean rejectIt()
	{
		log.info("rejectIt - " + toString());
		setIsApproved(false);
		return true;
	}	//	rejectIt
	
	/**
	 * 	Complete Document
	 * 	@return new status (Complete, In Progress, Invalid, Waiting ..)
	 */
	public String completeIt()
	{
		//	Re-Check
		if (!m_justPrepared)
		{
			String status = prepareIt();
			if (!DocAction.STATUS_InProgress.equals(status))
				return status;
		}

		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_COMPLETE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;
		
		//	Implicit Approval
		if (!isApproved())
			approveIt();
		log.info(toString());
		//

		List<MZMassiveInvLine> massiveInvLineList = this.getSelectedLines();
		if (massiveInvLineList.size() <= 0){
			m_processMsg = "No hay lineas marcadas para procesar";
			return DocAction.STATUS_Invalid;
		}

		Timestamp today = TimeUtil.trunc(new Timestamp (System.currentTimeMillis()), TimeUtil.TRUNC_DAY);

		// CORREGIR: la ubicación debería estar en cada linea de la reserva, hoy no lo esta.
		MLocator locator = ((MWarehouse) this.getM_Warehouse()).getDefaultLocator();

		for (MZMassiveInvLine massiveInvLine: massiveInvLineList){
			// Genero InOut siempre.
			// La diferencia solamente esta en el documento: si se indica generar Solo Factura, entonces documento = entrega
			// Si se indica generar remito o remito y factura, entonces documento = remito de entrega
			// Esta diferencia en el documento tiene que ver con facturación electrónica, ya que los remitos de entrega son electrónicos
			// y debe llevar el consecutivo en la numeración, mientras que las entregas normales no.
			MInOut inOut = new MInOut(getCtx(), 0, get_TrxName());
			inOut.setM_Warehouse_ID(this.getM_Warehouse_ID());
			inOut.setIsSOTrx(true);
			inOut.setAD_Org_ID(this.getAD_Org_ID());

			// CORREGIR: debo tomar documento segun sea entrega normal o eRemito, desde la configuración comercial. Hoy falta esa configuración.
			//inOut.setC_DocType_ID(comercialConfig.getDefaultDocMMR_ID());
			inOut.setC_DocType_ID(1000011);

			inOut.setDescription("Generada en Facturacion Masiva: " + this.getDocumentNo());
			inOut.setMovementType("C-");
			inOut.setMovementDate(today);
			inOut.setC_BPartner_ID(massiveInvLine.getC_BPartner_ID());
			inOut.setC_BPartner_Location_ID(massiveInvLine.getC_BPartner_Location_ID());
			inOut.setDeliveryRule(X_M_InOut.DELIVERYRULE_Availability);
			inOut.setFreightCostRule(X_M_InOut.FREIGHTCOSTRULE_FreightIncluded);
			inOut.setDeliveryViaRule(X_M_InOut.DELIVERYVIARULE_Pickup);
			inOut.setPriorityRule(X_M_InOut.PRIORITYRULE_Medium);
			inOut.saveEx();

			// Genero lineas de entrega, recorriendo lineas de la reserva asociada a esta linea de facturación masiva
			MZReservaVta reservaVta = new MZReservaVta(getCtx(), massiveInvLine.getZ_ReservaVta_ID(), get_TrxName());
			List<MZReservaVtaLin> reservaVtaLinList = reservaVta.getLines();
			int lineNo = 0;
			for (MZReservaVtaLin reservaVtaLin: reservaVtaLinList){
				lineNo = lineNo + 10;

				MInOutLine inOutLine = new MInOutLine(inOut);
				inOutLine.setLine(lineNo);

				// CORREGIR: la ubicación debería estar en cada linea de la reserva, hoy no lo esta.
				inOutLine.setM_Locator_ID(locator.get_ID());

				inOutLine.setM_Product_ID(reservaVtaLin.getM_Product_ID());
				inOutLine.setC_UOM_ID(reservaVtaLin.getC_UOM_ID());
				inOutLine.setMovementQty(reservaVtaLin.getQtyReserved());
				inOutLine.setQtyEntered(reservaVtaLin.getQtyReservedEnt());
				inOutLine.saveEx();
			}
			// Completo entrega
			if (!inOut.processIt(DocAction.ACTION_Complete)){
				m_processMsg = "No se pudo Completar Documento de Entrega para Reserva : " + reservaVta.getDocumentNo();
				if (inOut.getProcessMsg() != null){
					m_processMsg += "\n" + inOut.getProcessMsg();
				}
				return DocAction.STATUS_Invalid;
			}
			inOut.saveEx();

			// Hago la factura si es necesario
			if (!this.getTypeMassiveInv().equalsIgnoreCase(X_Z_MassiveInv.TYPEMASSIVEINV_SOLOREMITO)){
				MInvoice invoice = new MInvoice(getCtx(), 0, get_TrxName());
				invoice.setAD_Org_ID(this.getAD_Org_ID());
				invoice.setIsSOTrx(true);

				// CORREGIR: Debo tomar documento desde configuracion comercial
				invoice.setC_DocTypeTarget_ID(1000002);
				invoice.setC_DocType_ID(1000002);

				invoice.setDateInvoiced(today);
				invoice.setDateAcct(today);
				invoice.setC_BPartner_ID(massiveInvLine.getC_BPartner_ID());
				invoice.setC_BPartner_Location_ID(massiveInvLine.getC_BPartner_Location_ID());
				invoice.setC_Currency_ID(massiveInvLine.getC_Currency_ID());
				invoice.setPaymentRule(X_C_Invoice.PAYMENTRULE_OnCredit);

				// CORREGIR: Debo tomar termino de pago desde el socio de negocio
				invoice.setC_PaymentTerm_ID(1000000);

				invoice.setTotalLines(Env.ZERO);
				invoice.setGrandTotal(Env.ZERO);
				invoice.set_ValueOfColumn("AmtSubtotal", Env.ZERO);

				MOrder order = (MOrder) reservaVta.getC_Order();
				invoice.setM_PriceList_ID(order.getM_PriceList_ID());
				invoice.setIsTaxIncluded(order.isTaxIncluded());
				invoice.set_ValueOfColumn("EstadoAprobacion", "APROBADO");
				invoice.set_ValueOfColumn("TipoFormaPago", "CREDITO");
				invoice.saveEx();

				// Genero lineas de entrega, recorriendo lineas de la reserva asociada a esta linea de facturación masiva
				lineNo = 0;
				for (MZReservaVtaLin reservaVtaLin: reservaVtaLinList){
					lineNo = lineNo + 10;

					MInvoiceLine invoiceLine = new MInvoiceLine(invoice);
					invoiceLine.setLine(lineNo);
					invoiceLine.setAD_Org_ID(invoice.getAD_Org_ID());
					invoiceLine.setM_Product_ID(reservaVtaLin.getM_Product_ID());
					invoiceLine.setC_UOM_ID(reservaVtaLin.getC_UOM_ID());
					invoiceLine.setQtyEntered(reservaVtaLin.getQtyReservedEnt());
					invoiceLine.setQtyInvoiced(reservaVtaLin.getQtyReserved());
					MOrderLine orderLine = (MOrderLine) reservaVtaLin.getC_OrderLine();
					invoiceLine.setPriceEntered(orderLine.getPriceEntered());
					invoiceLine.setPriceActual(orderLine.getPriceActual());
					invoiceLine.setLineNetAmt(orderLine.getLineNetAmt());
					invoiceLine.set_ValueOfColumn("AmtSubTotal", orderLine.get_Value("AmtSubTotal"));
					invoiceLine.setC_Tax_ID(orderLine.getC_Tax_ID());
					invoiceLine.setTaxAmt((BigDecimal)orderLine.get_Value("TaxAmt"));
					invoiceLine.setLineNetAmt();
					invoiceLine.saveEx();
				}
				if (!invoice.processIt(DocAction.ACTION_Complete)){
					m_processMsg = "No se pudo Completar Factura para Reserva : " + reservaVta.getDocumentNo();
					if (inOut.getProcessMsg() != null){
						m_processMsg += "\n" + inOut.getProcessMsg();
					}
					return DocAction.STATUS_Invalid;
				}
				else{
					invoice.saveEx();
				}
			}
		}

		//	User Validation
		String valid = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_COMPLETE);
		if (valid != null)
		{
			m_processMsg = valid;
			return DocAction.STATUS_Invalid;
		}
		//	Set Definitive Document No
		setDefiniteDocumentNo();

		setProcessed(true);
		setDocAction(DOCACTION_Close);
		return DocAction.STATUS_Completed;
	}	//	completeIt
	
	/**
	 * 	Set the definite document number after completed
	 */
	private void setDefiniteDocumentNo() {
		MDocType dt = MDocType.get(getCtx(), getC_DocType_ID());
		if (dt.isOverwriteDateOnComplete()) {
			setDateDoc(new Timestamp(System.currentTimeMillis()));
		}
		if (dt.isOverwriteSeqOnComplete()) {
			String value = null;
			int index = p_info.getColumnIndex("C_DocType_ID");
			if (index == -1)
				index = p_info.getColumnIndex("C_DocTypeTarget_ID");
			if (index != -1)		//	get based on Doc Type (might return null)
				value = DB.getDocumentNo(get_ValueAsInt(index), get_TrxName(), true);
			if (value != null) {
				setDocumentNo(value);
			}
		}
	}

	/**
	 * 	Void Document.
	 * 	Same as Close.
	 * 	@return true if success 
	 */
	public boolean voidIt()
	{
		log.info("voidIt - " + toString());
		return closeIt();
	}	//	voidIt
	
	/**
	 * 	Close Document.
	 * 	Cancel not delivered Qunatities
	 * 	@return true if success 
	 */
	public boolean closeIt()
	{
		log.info("closeIt - " + toString());

		//	Close Not delivered Qty
		setDocAction(DOCACTION_None);
		return true;
	}	//	closeIt
	
	/**
	 * 	Reverse Correction
	 * 	@return true if success 
	 */
	public boolean reverseCorrectIt()
	{
		log.info("reverseCorrectIt - " + toString());
		return false;
	}	//	reverseCorrectionIt
	
	/**
	 * 	Reverse Accrual - none
	 * 	@return true if success 
	 */
	public boolean reverseAccrualIt()
	{
		log.info("reverseAccrualIt - " + toString());
		return false;
	}	//	reverseAccrualIt
	
	/** 
	 * 	Re-activate
	 * 	@return true if success 
	 */
	public boolean reActivateIt()
	{
		log.info("reActivateIt - " + toString());
		setProcessed(false);
		if (reverseCorrectIt())
			return true;
		return false;
	}	//	reActivateIt
	
	
	/*************************************************************************
	 * 	Get Summary
	 *	@return Summary of Document
	 */
	public String getSummary()
	{
		StringBuffer sb = new StringBuffer();
		sb.append(getDocumentNo());
	//	sb.append(": ")
	//		.append(Msg.translate(getCtx(),"TotalLines")).append("=").append(getTotalLines())
	//		.append(" (#").append(getLines(false).length).append(")");
		//	 - Description
		if (getDescription() != null && getDescription().length() > 0)
			sb.append(" - ").append(getDescription());
		return sb.toString();
	}	//	getSummary

	/**
	 * 	Get Process Message
	 *	@return clear text error message
	 */
	public String getProcessMsg()
	{
		return m_processMsg;
	}	//	getProcessMsg
	
	/**
	 * 	Get Document Owner (Responsible)
	 *	@return AD_User_ID
	 */
	public int getDoc_User_ID()
	{
	//	return getSalesRep_ID();
		return 0;
	}	//	getDoc_User_ID

	/**
	 * 	Get Document Approval Amount
	 *	@return amount
	 */
	public BigDecimal getApprovalAmt()
	{
		return null;	//getTotalLines();
	}	//	getApprovalAmt
	
	/**
	 * 	Get Document Currency
	 *	@return C_Currency_ID
	 */
	public int getC_Currency_ID()
	{
	//	MPriceList pl = MPriceList.get(getCtx(), getM_PriceList_ID());
	//	return pl.getC_Currency_ID();
		return 0;
	}	//	getC_Currency_ID

    @Override
    public String toString()
    {
      StringBuffer sb = new StringBuffer ("MZMassiveInv[")
        .append(getSummary()).append("]");
      return sb.toString();
    }

	/**
	 * Obtiene información de ordenes de venta a considerarse en este proceso, según los filtros indicados por el usuario.
	 * @return
	 */
	public String getData() {
		try{
			// Elimino generacion anterior en caso de que el usuario asi lo indique
			this.deleteDocuments();
			// Obtiene ordenes de venta segun criterios de búsqueda y genera lineas.
			this.getOrders();
		}
		catch (Exception e){
			throw new AdempiereException(e);
		}
		return null;
	}

	/**
	 * Elimina información actual antes de reprocesar.
	 * Xpande. Created by Gabriel Vila on 6/15/21.
	 */
	private void deleteDocuments() {

		String action = "";
		try{
			action = " delete from Z_MassiveInvLine where z_massiveinv_id =" + this.get_ID();
			DB.executeUpdateEx(action, get_TrxName());
		}
		catch (Exception e){
			throw new AdempiereException(e);
		}
	}

	/***
	 * Obtiene lineas de ordenes de venta a considerar ordenadas por producto y genera
	 * lineas por cada una de ellas.
	 * Xpande. Created by Gabriel Vila on 8/1/20.
	 * @return
	 */
	private void getOrders(){

		String sql = "", action = "";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try{
			String whereClause = this.getWhereDocuments();
			if (whereClause == null){
				throw new AdempiereException("Se debe indicar Asignaciones de Transporte, Socios de Negocio o Pickings a Procesar");
			}
			// Query
			sql = " SELECT rvta.z_reservavta_id, rvta.c_order_id, rvta.dateordered, rvta.datedoc as datereserved, coalesce(rvta.z_asignatrlog_id, 0) as z_asignatrlog_id, " +
					" o.c_doctypetarget_id AS c_doctype_id,  o.documentno, rvta.c_bpartner_id, o.c_bpartner_location_id, o.c_currency_id, rvta.z_picking_id,  o.salesrep_id, " +
					" o.poreference, o.datepromised, rvta.m_warehouse_id, bpl.c_salesregion_id " +
					" FROM z_reservavta rvta " +
					" join c_order o on rvta.c_order_id = o.c_order_id " +
					" JOIN c_bpartner_location bpl ON o.c_bpartner_location_id = bpl.c_bpartner_location_id " +
					" left outer join z_asignatrlog atr on (rvta.z_asignatrlog_id = atr.z_asignatrlog_id and atr.z_picking_id is null) " +
					" WHERE rvta.docstatus = 'CO' " +
					" AND rvta.z_picking_id is not null " + whereClause;

			pstmt = DB.prepareStatement(sql, get_TrxName());
			rs = pstmt.executeQuery();

			while(rs.next()){
				MZMassiveInvLine massiveInvLine = new MZMassiveInvLine(getCtx(), 0, get_TrxName());
				massiveInvLine.setZ_MassiveInv_ID(this.get_ID());
				massiveInvLine.setZ_Picking_ID(rs.getInt("z_picking_id"));
				massiveInvLine.setZ_ReservaVta_ID(rs.getInt("z_reservavta_id"));
				if (rs.getInt("z_asignatrlog_id") > 0){
					massiveInvLine.setZ_AsignaTrLog_ID(rs.getInt("z_asignatrlog_id"));
				}
				massiveInvLine.setC_BPartner_ID(rs.getInt("c_bpartner_id"));
				massiveInvLine.setC_Order_ID(rs.getInt("c_order_id"));
				massiveInvLine.setDateOrdered(rs.getTimestamp("dateordered"));
				massiveInvLine.setC_BPartner_Location_ID(rs.getInt("c_bpartner_location_id"));
				massiveInvLine.setC_Currency_ID(rs.getInt("c_currency_id"));
				massiveInvLine.setDateReserved(rs.getTimestamp("datereserved"));
				massiveInvLine.setIsSelected(true);
				massiveInvLine.setTotalAmt(Env.ZERO);
				massiveInvLine.saveEx();
			}
		}
		catch (Exception e){
			throw new AdempiereException(e);
		}
		finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
	}

	/***
	 * Obtiene condiciones para obtener información de documentos a considerar en este proceso.
	 * Xpande. Created by Gabriel Vila on 8/1/20.
	 * @return
	 */
	private String getWhereDocuments(){
		try{
			String filtroATRs = this.getFiltroATRs();
			String filtroSocios = this.getFiltroSocios();
			String filtroPickings= this.getFiltroPickings();

			if ((filtroATRs == null) && (filtroSocios == null) && (filtroPickings == null)){
				return null;
			}

			if ((filtroATRs != null) && (filtroSocios == null) && (filtroPickings == null)){
				return " AND " + filtroATRs;
			}
			if ((filtroATRs == null) && (filtroSocios != null) && (filtroPickings == null)){
				return " AND " + filtroSocios;
			}
			if ((filtroATRs == null) && (filtroSocios == null) && (filtroPickings != null)){
				return " AND " + filtroPickings;
			}

			if ((filtroATRs != null) && (filtroSocios != null) && (filtroPickings == null)){
				return " AND ((" + filtroATRs + ") OR (" + filtroSocios + "))";
			}
			if ((filtroATRs != null) && (filtroSocios == null) && (filtroPickings != null)){
				return " AND ((" + filtroATRs + ") OR (" + filtroPickings + "))";
			}
			if ((filtroATRs == null) && (filtroSocios != null) && (filtroPickings != null)){
				return " AND ((" + filtroSocios + ") OR (" + filtroPickings + "))";
			}
			if ((filtroATRs != null) && (filtroSocios != null) && (filtroPickings != null)){
				return " AND ((" + filtroATRs + ") OR (" + filtroSocios + ") OR (" + filtroPickings + "))";
			}
		}
		catch (Exception e){
			throw new AdempiereException(e);
		}
		return null;
	}

	/***
	 * Obtiene filtro de socios de negocio a aplicar para obtener documentos.
	 * Xpande. Created by Gabriel Vila on 12/5/19.
	 * @return
	 */
	private String getFiltroSocios() {

		String whereClause = null;

		try{
			// Verifico si tengo socios de negocio para filtrar
			String sql = " select count(*) from Z_MassiveInvFiltBP where z_massiveinv_id =" + this.get_ID();
			int contador = DB.getSQLValue(get_TrxName(), sql);

			// Si no tengo, no hago nada
			if (contador <= 0){
				return null;
			}
			// Tengo socios de negocio para filtrar
			whereClause = " rvta.c_bpartner_id IN (select c_bpartner_id from Z_MassiveInvFiltBP where z_massiveinv_id =" + this.get_ID() + ") ";
		}
		catch (Exception e){
			throw new AdempiereException(e);
		}

		return whereClause;
	}

	/***
	 * Obtiene filtro de asignaciones de transporte a aplicar para obtener documentos.
	 * Xpande. Created by Gabriel Vila on 12/5/19.
	 * @return
	 */
	private String getFiltroATRs() {

		String whereClause = null;

		try{
			// Verifico si tengo datos para filtrar
			String sql = " select count(*) from Z_MassiveInvFiltAtr where z_massiveinv_id =" + this.get_ID();
			int contador = DB.getSQLValue(get_TrxName(), sql);

			// Si no tengo, no hago nada
			if (contador <= 0){
				return null;
			}
			// Tengo datos para filtrar
			whereClause = " rvta.z_asignatrlog_id IN (select z_asignatrlog_id from Z_MassiveInvFiltAtr where z_massiveinv_id =" + this.get_ID() + ") ";
		}
		catch (Exception e){
			throw new AdempiereException(e);
		}

		return whereClause;
	}

	/***
	 * Obtiene filtro de pickings a aplicar para obtener documentos.
	 * Xpande. Created by Gabriel Vila on 12/5/19.
	 * @return
	 */
	private String getFiltroPickings() {

		String whereClause = null;

		try{
			// Verifico si tengo datos para filtrar
			String sql = " select count(*) from Z_MassiveInvFiltPick where z_massiveinv_id =" + this.get_ID();
			int contador = DB.getSQLValue(get_TrxName(), sql);

			// Si no tengo, no hago nada
			if (contador <= 0){
				return null;
			}
			// Tengo datos para filtrar
			whereClause = " rvta.z_picking_id IN (select z_picking_id from Z_MassiveInvFiltPick where z_massiveinv_id =" + this.get_ID() + ") ";
		}
		catch (Exception e){
			throw new AdempiereException(e);
		}

		return whereClause;
	}

	/**
	 * Obtiene y retorna lineas seleccionadas de este documento.
	 * @return
	 */
	public List<MZMassiveInvLine> getSelectedLines(){

		String whereClause = X_Z_MassiveInvLine.COLUMNNAME_Z_MassiveInv_ID + " =" + this.get_ID() +
				" AND " + X_Z_MassiveInvLine.COLUMNNAME_IsSelected + " ='Y' ";

		List<MZMassiveInvLine> lines = new Query(getCtx(), I_Z_MassiveInvLine.Table_Name, whereClause, get_TrxName()).list();

		return lines;
	}
}