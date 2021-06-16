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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.*;
import org.compiere.process.DocAction;
import org.compiere.process.DocOptions;
import org.compiere.process.DocumentEngine;
import org.compiere.util.DB;
import org.xpande.comercial.model.MZReservaVta;

/** Generated Model for Z_AsignaTrLog
 *  @author Adempiere (generated) 
 *  @version Release 3.9.0 - $Id$ */
public class MZAsignaTrLog extends X_Z_AsignaTrLog implements DocAction, DocOptions {

	/**
	 *
	 */
	private static final long serialVersionUID = 20200805L;

    /** Standard Constructor */
    public MZAsignaTrLog (Properties ctx, int Z_AsignaTrLog_ID, String trxName)
    {
      super (ctx, Z_AsignaTrLog_ID, trxName);
    }

    /** Load Constructor */
    public MZAsignaTrLog (Properties ctx, ResultSet rs, String trxName)
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

		// Obtengo lineas del documento
		List<MZAsignaTrLogLin> asignaTrLogLinList = this.getLines();

		// Valido que haya lineas
		if (asignaTrLogLinList.size() <= 0){
			m_processMsg = "El documento no tiene lineas de reseserva para procesar";
			return DocAction.STATUS_Invalid;
		}

		// Asocio documentos de reservas de venta a este documento de asignacion
		String action = " update z_reservavta set z_asignatrlog_id =" + this.get_ID() +
				" where z_reservavta_id in " +
				" (select z_reservavta_id from z_asignatrloglin where z_asignatrlog_id =" + this.get_ID() + ") ";
		DB.executeUpdateEx(action, get_TrxName());

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
		// Before reActivate
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_REACTIVATE);
		if (m_processMsg != null)
			return false;

		// Desasocio reservas contenidas en este documento.
		// Asocio documentos de reservas de venta a este documento de asignacion
		String action = " update z_reservavta set z_asignatrlog_id = null " +
				" where z_reservavta_id in " +
				" (select z_reservavta_id from z_asignatrloglin where z_asignatrlog_id =" + this.get_ID() + ") ";
		DB.executeUpdateEx(action, get_TrxName());

		// After reActivate
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_REACTIVATE);
		if (m_processMsg != null)
			return false;

		this.setProcessed(false);
		this.setDocStatus(DOCSTATUS_InProgress);
		this.setDocAction(DOCACTION_Complete);

		return true;
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
      StringBuffer sb = new StringBuffer ("MZAsignaTrLog[")
        .append(getSummary()).append("]");
      return sb.toString();
    }

	/***
	 * Obtiene reservas de venta a procesar según filtros.
	 * Xpande. Created by Gabriel Vila on 8/6/20.
	 * @param tipoAccion
	 * @return
	 */
	public void getData(String tipoAccion) {

		try{
			boolean getDocumentosNuevos = true;

			if (!tipoAccion.equalsIgnoreCase("NUEVOS")){
				getDocumentosNuevos = false;
			}

			// Elimino generacion anterior en caso de que el usuario asi lo indique
			if (!getDocumentosNuevos){
				this.deleteDocuments();
			}

			// Obtiene ordenes de venta segun criterios de búsqueda y genera lineas.
			this.getReservas();
		}
		catch (Exception e){
			throw new AdempiereException(e);
		}
	}

	/***
	 * Elimina documentos existentes.
	 * Xpande. Created by Gabriel Vila on 8/6/20.
	 */
	private void deleteDocuments() {

		String action = "";

		try{

			action = " delete from z_asignatrloglin where z_asignatrlog_id =" + this.get_ID();
			DB.executeUpdateEx(action, get_TrxName());
		}
		catch (Exception e){
			throw new AdempiereException(e);
		}
	}

	/***
	 * Obtiene filtro de socios de negocio a aplicar para obtener documentos.
	 * Xpande. Created by Gabriel Vila on 8/6/20.
	 * @return
	 */
	private String getFiltroSocios() {

		String whereClause = null;

		try{

			// Verifico si tengo socios de negocio para filtrar
			String sql = " select count(*) from z_asignatrlogfiltbp where z_asignatrlog_id =" + this.get_ID();
			int contador = DB.getSQLValue(get_TrxName(), sql);

			// Si no tengo, no hago nada
			if (contador <= 0){
				return null;
			}

			// Tengo socios de negocio para filtrar
			whereClause = " hdr.c_bpartner_id IN (select c_bpartner_id from z_asignatrlogfiltbp where z_asignatrlog_id =" + this.get_ID() + ") ";
		}
		catch (Exception e){
			throw new AdempiereException(e);
		}

		return whereClause;
	}

	/***
	 * Obtiene filtro de regiones de venta a aplicar para obtener documentos.
	 * Xpande. Created by Gabriel Vila on 8/6/20.
	 * @return
	 */
	private String getFiltroRegiones() {

		String whereClause = null;

		try{

			// Verifico si tengo socios de negocio para filtrar
			String sql = " select count(*) from z_asignatrlogfiltreg where z_asignatrlog_id =" + this.get_ID();
			int contador = DB.getSQLValue(get_TrxName(), sql);

			// Si no tengo, no hago nada
			if (contador <= 0){
				return null;
			}

			// Tengo socios de negocio para filtrar
			whereClause = " hdr.c_salesregion_id IN (select c_salesregion_id from z_asignatrlogfiltreg where z_asignatrlog_id =" + this.get_ID() + ") ";
		}
		catch (Exception e){
			throw new AdempiereException(e);
		}

		return whereClause;
	}


	/***
	 * Obtiene condiciones para obtener información de documentos a considerar en este proceso.
	 * Xpande. Created by Gabriel Vila on 8/6/20.
	 * @return
	 */
	private String getWhereDocuments(){

		String whereClause = "";

		try{

			// Filtro almacen
			whereClause = "  and hdr.m_warehouse_id =" + this.getM_Warehouse_ID();

			// Filtros de fechas
			if (this.getDateOrderedFrom() != null){
				whereClause += " AND ord.DateOrdered >='" + this.getDateOrderedFrom() + "' ";
			}
			if (this.getDateOrderedTo() != null){
				whereClause += " AND ord.DateOrdered <='" + this.getDateOrderedTo() + "' ";
			}
			if (this.getDatePromisedFrom() != null){
				whereClause += " AND ord.DatePromised >='" + this.getDatePromisedFrom() + "' ";
			}
			if (this.getDatePromisedTo() != null){
				whereClause += " AND ord.DatePromised <='" + this.getDatePromisedTo() + "' ";
			}
			if (this.getDateReservedFrom() != null){
				whereClause += " AND hdr.DateDoc >='" + this.getDateReservedFrom() + "' ";
			}
			if (this.getDateReservedTo() != null){
				whereClause += " AND hdr.DateDoc <='" + this.getDateReservedTo() + "' ";
			}

			// Filtro de socios de negocio
			String filtroSocios = this.getFiltroSocios();
			if (filtroSocios != null){
				whereClause += " AND " + filtroSocios;
			}

			// Filtro de Regiones de Venta
			String filtroRegiones = this.getFiltroRegiones();
			if (filtroRegiones != null){
				whereClause += " AND " + filtroRegiones;
			}
		}
		catch (Exception e){
			throw new AdempiereException(e);
		}

		return whereClause;
	}

	/***
	 * Obtiene y carga documentos de reserva de venta a considerar en este proceso.
	 * Xpande. Created by Gabriel Vila on 8/6/20.
	 */
	private void getReservas(){

		String sql = "";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try{
			String whereClause = this.getWhereDocuments();

		    sql = " select hdr.z_reservavta_id, ord.dateordered, ord.datepromised, ord.poreference " +
					" from z_reservavta hdr " +
					" inner join c_order ord on hdr.c_order_id = ord.c_order_id " +
					" where hdr.ad_org_id =" + this.getAD_Org_ID() +
					" and hdr.docstatus ='CO' " +
					" and hdr.z_reservavta_id not in " +
					" (select z_reservavta_id from z_asignatrloglin where z_asignatrlog_id =" + this.get_ID() + ") " +
					" and hdr.z_asignatrlog_id is null " + whereClause;

			pstmt = DB.prepareStatement(sql, get_TrxName());
			rs = pstmt.executeQuery();

			while(rs.next()){

				// Instancio modelo de reserva
				MZReservaVta reservaVta = new MZReservaVta(getCtx(), rs.getInt("z_reservavta_id"), get_TrxName());

				// Genero linea de este documento
				MZAsignaTrLogLin asignaTrLogLin = new MZAsignaTrLogLin(getCtx(), 0, get_TrxName());
				asignaTrLogLin.setZ_AsignaTrLog_ID(this.get_ID());
				asignaTrLogLin.setAD_Org_ID(this.getAD_Org_ID());
				asignaTrLogLin.setZ_ReservaVta_ID(reservaVta.get_ID());
				asignaTrLogLin.setC_BPartner_ID(reservaVta.getC_BPartner_ID());
				asignaTrLogLin.setC_BPartner_Location_ID(reservaVta.getC_BPartner_Location_ID());
				asignaTrLogLin.setC_Order_ID(reservaVta.getC_Order_ID());
				asignaTrLogLin.setC_Currency_ID(reservaVta.getC_Currency_ID());
				asignaTrLogLin.setC_DocType_ID(reservaVta.getC_DocType_ID());
				asignaTrLogLin.setDateOrdered(rs.getTimestamp("DateOrdered"));
				asignaTrLogLin.setDatePromised(rs.getTimestamp("DatePromised"));
				asignaTrLogLin.setDateReserved(reservaVta.getDateDoc());
				asignaTrLogLin.setM_Warehouse_ID(reservaVta.getM_Warehouse_ID());
				asignaTrLogLin.setTotalAmt(reservaVta.getTotalAmt());
				asignaTrLogLin.setPOReference(rs.getString("POReference"));

				if (reservaVta.getC_SalesRegion_ID() > 0){
					asignaTrLogLin.setC_SalesRegion_ID(reservaVta.getC_SalesRegion_ID());
				}
				if (reservaVta.getSalesRep_ID() > 0){
					asignaTrLogLin.setSalesRep_ID(reservaVta.getSalesRep_ID());
				}

				asignaTrLogLin.saveEx();
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
	 * Obtiene y retorna lineas de este documento.
	 * Xpande. Created by Gabriel Vila on 8/6/20.
	 * @return
	 */
	public List<MZAsignaTrLogLin> getLines(){

		String whereClause = X_Z_AsignaTrLogLin.COLUMNNAME_Z_AsignaTrLog_ID + " =" + this.get_ID();

		List<MZAsignaTrLogLin> lines = new Query(getCtx(), I_Z_AsignaTrLogLin.Table_Name, whereClause, get_TrxName()).list();

		return lines;
	}


}