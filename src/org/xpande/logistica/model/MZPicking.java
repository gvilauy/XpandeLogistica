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
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.*;
import org.compiere.process.DocAction;
import org.compiere.process.DocOptions;
import org.compiere.process.DocumentEngine;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.xpande.comercial.model.*;

/** Generated Model for Z_Picking
 *  @author Adempiere (generated) 
 *  @version Release 3.9.0 - $Id$ */
public class MZPicking extends X_Z_Picking implements DocAction, DocOptions {

	/**
	 *
	 */
	private static final long serialVersionUID = 20200812L;

    /** Standard Constructor */
    public MZPicking (Properties ctx, int Z_Picking_ID, String trxName)
    {
      super (ctx, Z_Picking_ID, trxName);
    }

    /** Load Constructor */
    public MZPicking (Properties ctx, ResultSet rs, String trxName)
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

			//options[newIndex++] = DocumentEngine.ACTION_None;
			options[newIndex++] = DocumentEngine.ACTION_ReActivate;
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
      StringBuffer sb = new StringBuffer ("MZPicking[")
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
			action = " delete from Z_PickingBPView cascade where z_picking_id =" + this.get_ID();
			DB.executeUpdateEx(action, get_TrxName());

			action = " delete from Z_PickingProd cascade where z_picking_id =" + this.get_ID();
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
				throw new AdempiereException("Se debe indicar Asignaciones de Transporte y/o Socios de Negocio a Procesar");
			}

			// Query
			sql = "  SELECT rvta.z_reservaventa_id, rvta.c_order_id, rvtal.c_orderline_id, " +
					" rvta.dateordered, rvta.z_asignatrlog_id, o.c_doctypetarget_id AS c_doctype_id, " +
					" o.documentno, o.c_bpartner_id, o.c_bpartner_location_id, o.c_currency_id," +
					" o.salesrep_id, o.poreference, o.datepromised, rvtal.m_product_id, rvtal.c_uom_id, prod.c_uom_id AS c_uom_to_id, " +
					" COALESCE(rvtal.uommultiplyrate, 1::numeric) AS uommultiplyrate, rvtal.qtyreserved, rvtal.qtyreservedent, " +
					" rvta.m_warehouse_id, bpl.c_salesregion_id" +
					" FROM z_reservavta rvta " +
					" join z_reservavtalin rvtal on rvta.z_reservavta_id = rvtal.z_reservavta_id " +
					" join c_order o on rvta.c_order_id = o.c_order_id " +
					" JOIN c_orderline ol ON rvtal.c_orderline_id = ol.c_orderline_id " +
					" JOIN c_bpartner_location bpl ON o.c_bpartner_location_id = bpl.c_bpartner_location_id " +
					" JOIN m_product prod ON ol.m_product_id = prod.m_product_id " +
					" left outer join z_asignatrlog atr on (rvta.z_asignatrlog_id = atr.z_asignatrlog_id and atr.z_picking_id is null) " +
					" WHERE rvta.docstatus = 'CO' " + whereClause +
					" order by rvtal.m_product_id, rvta.dateordered, rvta.c_order_id ";

			int mProductIDAux = 0;
			MZPickingProd pickingProd = null;
			MZPickingBPView pickingBP = null;

			pstmt = DB.prepareStatement(sql, get_TrxName());
			rs = pstmt.executeQuery();

			while(rs.next()){

				// Corte por producto
				if (rs.getInt("m_product_id") != mProductIDAux){

					mProductIDAux = rs.getInt("m_product_id");

					// Obtengo modelo de producto a considerar, si ya existe.
					// Si no existe lo creo ahora
					pickingProd = this.getPickingProd(mProductIDAux);
					if ((pickingProd == null) || (pickingProd.get_ID() <= 0)){
						pickingProd = new MZPickingProd(getCtx(), 0, get_TrxName());
						pickingProd.setZ_Picking_ID(this.get_ID());
						pickingProd.setM_Product_ID(mProductIDAux);
						pickingProd.setC_UOM_ID(rs.getInt("c_uom_to_id"));
						pickingProd.setQtyPicking(Env.ZERO);
						pickingProd.setQtyPickingEnt(Env.ZERO);
						pickingProd.setQtyConfirmed(Env.ZERO);
						pickingProd.setQtyConfirmedEnt(Env.ZERO);
						pickingProd.saveEx();
					}
				}
				// Si se indica tipo de proceso por Socio de negocio
				if (this.getTipoPicking().equalsIgnoreCase(X_Z_Picking.TIPOPICKING_SOCIODENEGOCIO)){
					// Obtengo modelo de socio a considerar, si ya existe
					// Si no existe lo creo ahora
					pickingBP = this.getPickingBP(rs.getInt("c_bpartner_id"));
					if ((pickingBP == null) || (pickingBP.get_ID() <= 0)){
						MBPartner partner = new MBPartner(getCtx(), rs.getInt("c_bpartner_id"), null);
						pickingBP = new MZPickingBPView(getCtx(), 0, get_TrxName());
						pickingBP.setZ_Picking_ID(this.get_ID());
						pickingBP.setC_BPartner_ID(rs.getInt("c_bpartner_id"));
						pickingBP.setTaxID(partner.getTaxID());

						if (partner.get_ValueAsInt("Z_CanalVenta_ID") > 0){
							pickingBP.setZ_CanalVenta_ID(partner.get_ValueAsInt("Z_CanalVenta_ID"));
						}
						pickingBP.saveEx();
					}
				}

				MZPickingLin pickingLin = new MZPickingLin(getCtx(), 0, get_TrxName());
				pickingLin.setZ_Picking_ID(this.get_ID());
				pickingLin.setZ_PickingProd_ID(pickingProd.get_ID());

				if (pickingBP != null){
					pickingLin.setZ_PickingBPView_ID(pickingBP.get_ID());
				}

				pickingLin.setAD_Org_ID(this.getAD_Org_ID());
				pickingLin.setC_Order_ID(rs.getInt("c_order_id"));
				pickingLin.setC_OrderLine_ID(rs.getInt("c_orderline_id"));
				pickingLin.setC_BPartner_ID(rs.getInt("c_bpartner_id"));
				pickingLin.setC_BPartner_Location_ID(rs.getInt("c_bpartner_location_id"));
				pickingLin.setDateOrdered(rs.getTimestamp("dateordered"));
				pickingLin.setM_Product_ID(rs.getInt("m_product_id"));
				pickingLin.setC_UOM_ID(rs.getInt("c_uom_id"));
				pickingLin.setC_UOM_To_ID(rs.getInt("c_uom_to_id"));
				pickingLin.setUomMultiplyRate(rs.getBigDecimal("UomMultiplyRate"));
				pickingLin.setQtyPickingEnt(rs.getBigDecimal("qtyreservedent"));
				pickingLin.setQtyConfirmedEnt(Env.ZERO);
				pickingLin.setQtyConfirmed(Env.ZERO);
				pickingLin.saveEx();

				// Convierto cantidades según factor a unidad de esta linea
				if (rs.getInt("c_uom_id") != rs.getInt("c_uom_to_id")){
					MUOM uomTo = (MUOM) pickingLin.getC_UOM();
					pickingLin.setQtyPicking(pickingLin.getQtyPickingEnt().multiply(pickingLin.getUomMultiplyRate()).setScale(uomTo.getStdPrecision(), RoundingMode.HALF_UP));
				}
				else{
					pickingLin.setQtyPicking(pickingLin.getQtyPickingEnt());
				}
				pickingLin.saveEx();

				// Actualizo cantidades totalizando en el producto
				pickingProd.setQtyPicking(pickingProd.getQtyPicking().add(pickingLin.getQtyPicking()));
				pickingProd.setQtyPickingEnt(pickingProd.getQtyPickingEnt().add(pickingLin.getQtyPickingEnt()));
				pickingProd.saveEx();
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

			if ((filtroATRs == null) && (filtroSocios == null)){
				return null;
			}

			if ((filtroATRs != null) && (filtroSocios == null)){
				return " AND " + filtroATRs;
			}
			if ((filtroATRs == null) && (filtroSocios != null)){
				return " AND " + filtroSocios;
			}
			if ((filtroATRs != null) && (filtroSocios != null)){
				return " AND ((" + filtroATRs + ") OR (" + filtroSocios + "))";
			}

		}
		catch (Exception e){
			throw new AdempiereException(e);
		}
		return null;
	}

	/**
	 * Obtiene y retorna modelo de producto asociado a picking.
	 * @param mProductID
	 * @return
	 */
	private MZPickingProd getPickingProd(int mProductID) {

		String whereClause = X_Z_PickingProd.COLUMNNAME_Z_Picking_ID + " =" + this.get_ID() +
				" AND " + X_Z_PickingProd.COLUMNNAME_M_Product_ID + " =" + mProductID;

		MZPickingProd model = new Query(getCtx(), I_Z_PickingProd.Table_Name, whereClause, get_TrxName()).first();

		return model;
	}

	/**
	 * Obtiene y retorna modelo de socio de negocio asociado a picking
	 * @param cBPartnerID
	 * @return
	 */
	private MZPickingBPView getPickingBP(int cBPartnerID) {

		String whereClause = X_Z_PickingBPView.COLUMNNAME_Z_Picking_ID + " =" + this.get_ID() +
				" AND " + X_Z_PickingBPView.COLUMNNAME_C_BPartner_ID + " =" + cBPartnerID;

		MZPickingBPView model = new Query(getCtx(), I_Z_PickingBPView.Table_Name, whereClause, get_TrxName()).first();

		return model;
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
			String sql = " select count(*) from Z_PickingFiltBP where z_picking_id =" + this.get_ID();
			int contador = DB.getSQLValue(get_TrxName(), sql);

			// Si no tengo, no hago nada
			if (contador <= 0){
				return null;
			}
			// Tengo socios de negocio para filtrar
			whereClause = " rvta.c_bpartner_id IN (select c_bpartner_id from Z_PickingFiltBP where z_picking_id =" + this.get_ID() + ") ";
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
			String sql = " select count(*) from Z_PickingFiltAtr where z_picking_id =" + this.get_ID();
			int contador = DB.getSQLValue(get_TrxName(), sql);

			// Si no tengo, no hago nada
			if (contador <= 0){
				return null;
			}
			// Tengo socios de negocio para filtrar
			whereClause = " rvta.z_asignalogtr_id IN (select c_bpartner_id from Z_PickingFiltAtr where z_picking_id =" + this.get_ID() + ") ";
		}
		catch (Exception e){
			throw new AdempiereException(e);
		}

		return whereClause;
	}

}