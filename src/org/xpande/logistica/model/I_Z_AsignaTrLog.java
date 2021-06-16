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

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for Z_AsignaTrLog
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1
 */
public interface I_Z_AsignaTrLog 
{

    /** TableName=Z_AsignaTrLog */
    public static final String Table_Name = "Z_AsignaTrLog";

    /** AD_Table_ID=1000255 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name C_DocType_ID */
    public static final String COLUMNNAME_C_DocType_ID = "C_DocType_ID";

	/** Set Document Type.
	  * Document type or rules
	  */
	public void setC_DocType_ID (int C_DocType_ID);

	/** Get Document Type.
	  * Document type or rules
	  */
	public int getC_DocType_ID();

	public I_C_DocType getC_DocType() throws RuntimeException;

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name DateDoc */
    public static final String COLUMNNAME_DateDoc = "DateDoc";

	/** Set Document Date.
	  * Date of the Document
	  */
	public void setDateDoc (Timestamp DateDoc);

	/** Get Document Date.
	  * Date of the Document
	  */
	public Timestamp getDateDoc();

    /** Column name DateOrderedFrom */
    public static final String COLUMNNAME_DateOrderedFrom = "DateOrderedFrom";

	/** Set DateOrderedFrom.
	  * Fecha orden desde
	  */
	public void setDateOrderedFrom (Timestamp DateOrderedFrom);

	/** Get DateOrderedFrom.
	  * Fecha orden desde
	  */
	public Timestamp getDateOrderedFrom();

    /** Column name DateOrderedTo */
    public static final String COLUMNNAME_DateOrderedTo = "DateOrderedTo";

	/** Set DateOrderedTo.
	  * Fecha Orden Hasta
	  */
	public void setDateOrderedTo (Timestamp DateOrderedTo);

	/** Get DateOrderedTo.
	  * Fecha Orden Hasta
	  */
	public Timestamp getDateOrderedTo();

    /** Column name DatePromisedFrom */
    public static final String COLUMNNAME_DatePromisedFrom = "DatePromisedFrom";

	/** Set DatePromisedFrom.
	  * Fecha prometida desde
	  */
	public void setDatePromisedFrom (Timestamp DatePromisedFrom);

	/** Get DatePromisedFrom.
	  * Fecha prometida desde
	  */
	public Timestamp getDatePromisedFrom();

    /** Column name DatePromisedTo */
    public static final String COLUMNNAME_DatePromisedTo = "DatePromisedTo";

	/** Set DatePromisedTo.
	  * Fecha prometida hasta
	  */
	public void setDatePromisedTo (Timestamp DatePromisedTo);

	/** Get DatePromisedTo.
	  * Fecha prometida hasta
	  */
	public Timestamp getDatePromisedTo();

    /** Column name DateReservedFrom */
    public static final String COLUMNNAME_DateReservedFrom = "DateReservedFrom";

	/** Set DateReservedFrom.
	  * Fechas de Reserva Desde
	  */
	public void setDateReservedFrom (Timestamp DateReservedFrom);

	/** Get DateReservedFrom.
	  * Fechas de Reserva Desde
	  */
	public Timestamp getDateReservedFrom();

    /** Column name DateReservedTo */
    public static final String COLUMNNAME_DateReservedTo = "DateReservedTo";

	/** Set DateReservedTo.
	  * Fecha de Reserva Hasta
	  */
	public void setDateReservedTo (Timestamp DateReservedTo);

	/** Get DateReservedTo.
	  * Fecha de Reserva Hasta
	  */
	public Timestamp getDateReservedTo();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name DocAction */
    public static final String COLUMNNAME_DocAction = "DocAction";

	/** Set Document Action.
	  * The targeted status of the document
	  */
	public void setDocAction (String DocAction);

	/** Get Document Action.
	  * The targeted status of the document
	  */
	public String getDocAction();

    /** Column name DocStatus */
    public static final String COLUMNNAME_DocStatus = "DocStatus";

	/** Set Document Status.
	  * The current status of the document
	  */
	public void setDocStatus (String DocStatus);

	/** Get Document Status.
	  * The current status of the document
	  */
	public String getDocStatus();

    /** Column name DocumentNo */
    public static final String COLUMNNAME_DocumentNo = "DocumentNo";

	/** Set Document No.
	  * Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo);

	/** Get Document No.
	  * Document sequence number of the document
	  */
	public String getDocumentNo();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name IsApproved */
    public static final String COLUMNNAME_IsApproved = "IsApproved";

	/** Set Approved.
	  * Indicates if this document requires approval
	  */
	public void setIsApproved (boolean IsApproved);

	/** Get Approved.
	  * Indicates if this document requires approval
	  */
	public boolean isApproved();

    /** Column name M_Warehouse_ID */
    public static final String COLUMNNAME_M_Warehouse_ID = "M_Warehouse_ID";

	/** Set Warehouse.
	  * Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID);

	/** Get Warehouse.
	  * Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID();

	public I_M_Warehouse getM_Warehouse() throws RuntimeException;

    /** Column name ProcessButton */
    public static final String COLUMNNAME_ProcessButton = "ProcessButton";

	/** Set ProcessButton	  */
	public void setProcessButton (String ProcessButton);

	/** Get ProcessButton	  */
	public String getProcessButton();

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name ShowFilters */
    public static final String COLUMNNAME_ShowFilters = "ShowFilters";

	/** Set ShowFilters.
	  * Si se muestran o no filltros
	  */
	public void setShowFilters (boolean ShowFilters);

	/** Get ShowFilters.
	  * Si se muestran o no filltros
	  */
	public boolean isShowFilters();

    /** Column name TextoFiltro */
    public static final String COLUMNNAME_TextoFiltro = "TextoFiltro";

	/** Set TextoFiltro.
	  * Texto genérico para filtro de valores
	  */
	public void setTextoFiltro (String TextoFiltro);

	/** Get TextoFiltro.
	  * Texto genérico para filtro de valores
	  */
	public String getTextoFiltro();

    /** Column name TextoFiltro2 */
    public static final String COLUMNNAME_TextoFiltro2 = "TextoFiltro2";

	/** Set TextoFiltro2.
	  * Texto genérico para filtro de valores
	  */
	public void setTextoFiltro2 (String TextoFiltro2);

	/** Get TextoFiltro2.
	  * Texto genérico para filtro de valores
	  */
	public String getTextoFiltro2();

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();

    /** Column name UUID */
    public static final String COLUMNNAME_UUID = "UUID";

	/** Set Immutable Universally Unique Identifier.
	  * Immutable Universally Unique Identifier
	  */
	public void setUUID (String UUID);

	/** Get Immutable Universally Unique Identifier.
	  * Immutable Universally Unique Identifier
	  */
	public String getUUID();

    /** Column name Z_AsignaTrLog_ID */
    public static final String COLUMNNAME_Z_AsignaTrLog_ID = "Z_AsignaTrLog_ID";

	/** Set Z_AsignaTrLog ID	  */
	public void setZ_AsignaTrLog_ID (int Z_AsignaTrLog_ID);

	/** Get Z_AsignaTrLog ID	  */
	public int getZ_AsignaTrLog_ID();

    /** Column name Z_CanalVenta_ID */
    public static final String COLUMNNAME_Z_CanalVenta_ID = "Z_CanalVenta_ID";

	/** Set Z_CanalVenta ID	  */
	public void setZ_CanalVenta_ID (int Z_CanalVenta_ID);

	/** Get Z_CanalVenta ID	  */
	public int getZ_CanalVenta_ID();

    /** Column name Z_Picking_ID */
    public static final String COLUMNNAME_Z_Picking_ID = "Z_Picking_ID";

	/** Set Z_Picking ID	  */
	public void setZ_Picking_ID (int Z_Picking_ID);

	/** Get Z_Picking ID	  */
	public int getZ_Picking_ID();

	public I_Z_Picking getZ_Picking() throws RuntimeException;

    /** Column name Z_TransporteLog_ID */
    public static final String COLUMNNAME_Z_TransporteLog_ID = "Z_TransporteLog_ID";

	/** Set Z_TransporteLog ID	  */
	public void setZ_TransporteLog_ID (int Z_TransporteLog_ID);

	/** Get Z_TransporteLog ID	  */
	public int getZ_TransporteLog_ID();

	public I_Z_TransporteLog getZ_TransporteLog() throws RuntimeException;
}
