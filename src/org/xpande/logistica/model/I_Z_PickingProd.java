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

/** Generated Interface for Z_PickingProd
 *  @author Adempiere (generated) 
 *  @version Release 3.9.0
 */
public interface I_Z_PickingProd 
{

    /** TableName=Z_PickingProd */
    public static final String Table_Name = "Z_PickingProd";

    /** AD_Table_ID=1000262 */
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

    /** Column name CodigoProducto */
    public static final String COLUMNNAME_CodigoProducto = "CodigoProducto";

	/** Set CodigoProducto.
	  * Código de Producto
	  */
	public void setCodigoProducto (String CodigoProducto);

	/** Get CodigoProducto.
	  * Código de Producto
	  */
	public String getCodigoProducto();

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

    /** Column name C_UOM_ID */
    public static final String COLUMNNAME_C_UOM_ID = "C_UOM_ID";

	/** Set UOM.
	  * Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID);

	/** Get UOM.
	  * Unit of Measure
	  */
	public int getC_UOM_ID();

	public I_C_UOM getC_UOM() throws RuntimeException;

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

    /** Column name M_Product_ID */
    public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";

	/** Set Product.
	  * Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID);

	/** Get Product.
	  * Product, Service, Item
	  */
	public int getM_Product_ID();

	public I_M_Product getM_Product() throws RuntimeException;

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name QtyConfirmed */
    public static final String COLUMNNAME_QtyConfirmed = "QtyConfirmed";

	/** Set QtyConfirmed.
	  * Cantidad de unidades confirmadas expresadas en la unidad simple del producto
	  */
	public void setQtyConfirmed (BigDecimal QtyConfirmed);

	/** Get QtyConfirmed.
	  * Cantidad de unidades confirmadas expresadas en la unidad simple del producto
	  */
	public BigDecimal getQtyConfirmed();

    /** Column name QtyConfirmedEnt */
    public static final String COLUMNNAME_QtyConfirmedEnt = "QtyConfirmedEnt";

	/** Set QtyConfirmedEnt.
	  * Cantidad de unidades confirmadas expresadas en bultos
	  */
	public void setQtyConfirmedEnt (BigDecimal QtyConfirmedEnt);

	/** Get QtyConfirmedEnt.
	  * Cantidad de unidades confirmadas expresadas en bultos
	  */
	public BigDecimal getQtyConfirmedEnt();

    /** Column name QtyPicking */
    public static final String COLUMNNAME_QtyPicking = "QtyPicking";

	/** Set QtyPicking.
	  * Cantidad de unidades pickeadas expresadas en la unidad simple del producto
	  */
	public void setQtyPicking (BigDecimal QtyPicking);

	/** Get QtyPicking.
	  * Cantidad de unidades pickeadas expresadas en la unidad simple del producto
	  */
	public BigDecimal getQtyPicking();

    /** Column name QtyPickingEnt */
    public static final String COLUMNNAME_QtyPickingEnt = "QtyPickingEnt";

	/** Set QtyPickingEnt.
	  * Cantidad de unidades pickeadas expresadas en bultos
	  */
	public void setQtyPickingEnt (BigDecimal QtyPickingEnt);

	/** Get QtyPickingEnt.
	  * Cantidad de unidades pickeadas expresadas en bultos
	  */
	public BigDecimal getQtyPickingEnt();

    /** Column name SeqNo */
    public static final String COLUMNNAME_SeqNo = "SeqNo";

	/** Set Sequence.
	  * Method of ordering records;
 lowest number comes first
	  */
	public void setSeqNo (int SeqNo);

	/** Get Sequence.
	  * Method of ordering records;
 lowest number comes first
	  */
	public int getSeqNo();

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

    /** Column name Z_Picking_ID */
    public static final String COLUMNNAME_Z_Picking_ID = "Z_Picking_ID";

	/** Set Z_Picking ID	  */
	public void setZ_Picking_ID (int Z_Picking_ID);

	/** Get Z_Picking ID	  */
	public int getZ_Picking_ID();

	public I_Z_Picking getZ_Picking() throws RuntimeException;

    /** Column name Z_PickingProd_ID */
    public static final String COLUMNNAME_Z_PickingProd_ID = "Z_PickingProd_ID";

	/** Set Z_PickingProd ID	  */
	public void setZ_PickingProd_ID (int Z_PickingProd_ID);

	/** Get Z_PickingProd ID	  */
	public int getZ_PickingProd_ID();
}
