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
/** Generated Model - DO NOT CHANGE */
package org.xpande.logistica.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for Z_PickingProd
 *  @author Adempiere (generated) 
 *  @version Release 3.9.0 - $Id$ */
public class X_Z_PickingProd extends PO implements I_Z_PickingProd, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20200812L;

    /** Standard Constructor */
    public X_Z_PickingProd (Properties ctx, int Z_PickingProd_ID, String trxName)
    {
      super (ctx, Z_PickingProd_ID, trxName);
      /** if (Z_PickingProd_ID == 0)
        {
			setCodigoProducto (null);
			setC_UOM_ID (0);
			setM_Product_ID (0);
			setName (null);
			setQtyConfirmed (Env.ZERO);
			setQtyConfirmedEnt (Env.ZERO);
			setQtyPicking (Env.ZERO);
			setQtyPickingEnt (Env.ZERO);
			setSeqNo (0);
			setZ_Picking_ID (0);
			setZ_PickingProd_ID (0);
        } */
    }

    /** Load Constructor */
    public X_Z_PickingProd (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_Z_PickingProd[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set CodigoProducto.
		@param CodigoProducto 
		Código de Producto
	  */
	public void setCodigoProducto (String CodigoProducto)
	{
		set_Value (COLUMNNAME_CodigoProducto, CodigoProducto);
	}

	/** Get CodigoProducto.
		@return Código de Producto
	  */
	public String getCodigoProducto () 
	{
		return (String)get_Value(COLUMNNAME_CodigoProducto);
	}

	public I_C_UOM getC_UOM() throws RuntimeException
    {
		return (I_C_UOM)MTable.get(getCtx(), I_C_UOM.Table_Name)
			.getPO(getC_UOM_ID(), get_TrxName());	}

	/** Set UOM.
		@param C_UOM_ID 
		Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID)
	{
		if (C_UOM_ID < 1) 
			set_Value (COLUMNNAME_C_UOM_ID, null);
		else 
			set_Value (COLUMNNAME_C_UOM_ID, Integer.valueOf(C_UOM_ID));
	}

	/** Get UOM.
		@return Unit of Measure
	  */
	public int getC_UOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_UOM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_Product getM_Product() throws RuntimeException
    {
		return (I_M_Product)MTable.get(getCtx(), I_M_Product.Table_Name)
			.getPO(getM_Product_ID(), get_TrxName());	}

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1) 
			set_Value (COLUMNNAME_M_Product_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

	/** Set QtyConfirmed.
		@param QtyConfirmed 
		Cantidad de unidades confirmadas expresadas en la unidad simple del producto
	  */
	public void setQtyConfirmed (BigDecimal QtyConfirmed)
	{
		set_Value (COLUMNNAME_QtyConfirmed, QtyConfirmed);
	}

	/** Get QtyConfirmed.
		@return Cantidad de unidades confirmadas expresadas en la unidad simple del producto
	  */
	public BigDecimal getQtyConfirmed () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyConfirmed);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set QtyConfirmedEnt.
		@param QtyConfirmedEnt 
		Cantidad de unidades confirmadas expresadas en bultos
	  */
	public void setQtyConfirmedEnt (BigDecimal QtyConfirmedEnt)
	{
		set_Value (COLUMNNAME_QtyConfirmedEnt, QtyConfirmedEnt);
	}

	/** Get QtyConfirmedEnt.
		@return Cantidad de unidades confirmadas expresadas en bultos
	  */
	public BigDecimal getQtyConfirmedEnt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyConfirmedEnt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set QtyPicking.
		@param QtyPicking 
		Cantidad de unidades pickeadas expresadas en la unidad simple del producto
	  */
	public void setQtyPicking (BigDecimal QtyPicking)
	{
		set_Value (COLUMNNAME_QtyPicking, QtyPicking);
	}

	/** Get QtyPicking.
		@return Cantidad de unidades pickeadas expresadas en la unidad simple del producto
	  */
	public BigDecimal getQtyPicking () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyPicking);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set QtyPickingEnt.
		@param QtyPickingEnt 
		Cantidad de unidades pickeadas expresadas en bultos
	  */
	public void setQtyPickingEnt (BigDecimal QtyPickingEnt)
	{
		set_Value (COLUMNNAME_QtyPickingEnt, QtyPickingEnt);
	}

	/** Get QtyPickingEnt.
		@return Cantidad de unidades pickeadas expresadas en bultos
	  */
	public BigDecimal getQtyPickingEnt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyPickingEnt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Sequence.
		@param SeqNo 
		Method of ordering records; lowest number comes first
	  */
	public void setSeqNo (int SeqNo)
	{
		set_Value (COLUMNNAME_SeqNo, Integer.valueOf(SeqNo));
	}

	/** Get Sequence.
		@return Method of ordering records; lowest number comes first
	  */
	public int getSeqNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SeqNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Immutable Universally Unique Identifier.
		@param UUID 
		Immutable Universally Unique Identifier
	  */
	public void setUUID (String UUID)
	{
		set_Value (COLUMNNAME_UUID, UUID);
	}

	/** Get Immutable Universally Unique Identifier.
		@return Immutable Universally Unique Identifier
	  */
	public String getUUID () 
	{
		return (String)get_Value(COLUMNNAME_UUID);
	}

	public I_Z_Picking getZ_Picking() throws RuntimeException
    {
		return (I_Z_Picking)MTable.get(getCtx(), I_Z_Picking.Table_Name)
			.getPO(getZ_Picking_ID(), get_TrxName());	}

	/** Set Z_Picking ID.
		@param Z_Picking_ID Z_Picking ID	  */
	public void setZ_Picking_ID (int Z_Picking_ID)
	{
		if (Z_Picking_ID < 1) 
			set_Value (COLUMNNAME_Z_Picking_ID, null);
		else 
			set_Value (COLUMNNAME_Z_Picking_ID, Integer.valueOf(Z_Picking_ID));
	}

	/** Get Z_Picking ID.
		@return Z_Picking ID	  */
	public int getZ_Picking_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Z_Picking_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Z_PickingProd ID.
		@param Z_PickingProd_ID Z_PickingProd ID	  */
	public void setZ_PickingProd_ID (int Z_PickingProd_ID)
	{
		if (Z_PickingProd_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_Z_PickingProd_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_Z_PickingProd_ID, Integer.valueOf(Z_PickingProd_ID));
	}

	/** Get Z_PickingProd ID.
		@return Z_PickingProd ID	  */
	public int getZ_PickingProd_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Z_PickingProd_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}