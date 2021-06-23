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
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for Z_PickingLin
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_Z_PickingLin extends PO implements I_Z_PickingLin, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210622L;

    /** Standard Constructor */
    public X_Z_PickingLin (Properties ctx, int Z_PickingLin_ID, String trxName)
    {
      super (ctx, Z_PickingLin_ID, trxName);
      /** if (Z_PickingLin_ID == 0)
        {
			setC_BPartner_ID (0);
			setC_BPartner_Location_ID (0);
			setCodigoProducto (null);
			setC_Order_ID (0);
			setC_OrderLine_ID (0);
			setC_UOM_ID (0);
			setC_UOM_To_ID (0);
			setDateOrdered (new Timestamp( System.currentTimeMillis() ));
			setDateReserved (new Timestamp( System.currentTimeMillis() ));
			setM_Product_ID (0);
			setName (null);
			setQtyConfirmed (Env.ZERO);
			setQtyPicking (Env.ZERO);
			setQtyPickingEnt (Env.ZERO);
			setUomMultiplyRate (Env.ZERO);
			setZ_Picking_ID (0);
			setZ_PickingLin_ID (0);
			setZ_PickingProd_ID (0);
			setZ_ReservaVta_ID (0);
        } */
    }

    /** Load Constructor */
    public X_Z_PickingLin (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_Z_PickingLin[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_BPartner getC_BPartner() throws RuntimeException
    {
		return (I_C_BPartner)MTable.get(getCtx(), I_C_BPartner.Table_Name)
			.getPO(getC_BPartner_ID(), get_TrxName());	}

	/** Set Business Partner .
		@param C_BPartner_ID 
		Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner .
		@return Identifies a Business Partner
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_BPartner_Location getC_BPartner_Location() throws RuntimeException
    {
		return (I_C_BPartner_Location)MTable.get(getCtx(), I_C_BPartner_Location.Table_Name)
			.getPO(getC_BPartner_Location_ID(), get_TrxName());	}

	/** Set Partner Location.
		@param C_BPartner_Location_ID 
		Identifies the (ship to) address for this Business Partner
	  */
	public void setC_BPartner_Location_ID (int C_BPartner_Location_ID)
	{
		if (C_BPartner_Location_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_BPartner_Location_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_BPartner_Location_ID, Integer.valueOf(C_BPartner_Location_ID));
	}

	/** Get Partner Location.
		@return Identifies the (ship to) address for this Business Partner
	  */
	public int getC_BPartner_Location_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_Location_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	public I_C_Order getC_Order() throws RuntimeException
    {
		return (I_C_Order)MTable.get(getCtx(), I_C_Order.Table_Name)
			.getPO(getC_Order_ID(), get_TrxName());	}

	/** Set Order.
		@param C_Order_ID 
		Order
	  */
	public void setC_Order_ID (int C_Order_ID)
	{
		if (C_Order_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_Order_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Order_ID, Integer.valueOf(C_Order_ID));
	}

	/** Get Order.
		@return Order
	  */
	public int getC_Order_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Order_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_OrderLine getC_OrderLine() throws RuntimeException
    {
		return (I_C_OrderLine)MTable.get(getCtx(), I_C_OrderLine.Table_Name)
			.getPO(getC_OrderLine_ID(), get_TrxName());	}

	/** Set Sales Order Line.
		@param C_OrderLine_ID 
		Sales Order Line
	  */
	public void setC_OrderLine_ID (int C_OrderLine_ID)
	{
		if (C_OrderLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_OrderLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_OrderLine_ID, Integer.valueOf(C_OrderLine_ID));
	}

	/** Get Sales Order Line.
		@return Sales Order Line
	  */
	public int getC_OrderLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_OrderLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
			set_ValueNoCheck (COLUMNNAME_C_UOM_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_UOM_ID, Integer.valueOf(C_UOM_ID));
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

	public I_C_UOM getC_UOM_To() throws RuntimeException
    {
		return (I_C_UOM)MTable.get(getCtx(), I_C_UOM.Table_Name)
			.getPO(getC_UOM_To_ID(), get_TrxName());	}

	/** Set UoM To.
		@param C_UOM_To_ID 
		Target or destination Unit of Measure
	  */
	public void setC_UOM_To_ID (int C_UOM_To_ID)
	{
		if (C_UOM_To_ID < 1) 
			set_Value (COLUMNNAME_C_UOM_To_ID, null);
		else 
			set_Value (COLUMNNAME_C_UOM_To_ID, Integer.valueOf(C_UOM_To_ID));
	}

	/** Get UoM To.
		@return Target or destination Unit of Measure
	  */
	public int getC_UOM_To_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_UOM_To_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Date Ordered.
		@param DateOrdered 
		Date of Order
	  */
	public void setDateOrdered (Timestamp DateOrdered)
	{
		set_Value (COLUMNNAME_DateOrdered, DateOrdered);
	}

	/** Get Date Ordered.
		@return Date of Order
	  */
	public Timestamp getDateOrdered () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateOrdered);
	}

	/** Set DateReserved.
		@param DateReserved 
		Fecha de reserva
	  */
	public void setDateReserved (Timestamp DateReserved)
	{
		set_Value (COLUMNNAME_DateReserved, DateReserved);
	}

	/** Get DateReserved.
		@return Fecha de reserva
	  */
	public Timestamp getDateReserved () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateReserved);
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
			set_ValueNoCheck (COLUMNNAME_M_Product_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
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
		Cantidad de unidades confirmadas expresadas en unidad de medida
	  */
	public void setQtyConfirmedEnt (BigDecimal QtyConfirmedEnt)
	{
		set_Value (COLUMNNAME_QtyConfirmedEnt, QtyConfirmedEnt);
	}

	/** Get QtyConfirmedEnt.
		@return Cantidad de unidades confirmadas expresadas en unidad de medida
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
		Cantidad de unidades pickeadas expresadas en unidad de medida 
	  */
	public void setQtyPickingEnt (BigDecimal QtyPickingEnt)
	{
		set_Value (COLUMNNAME_QtyPickingEnt, QtyPickingEnt);
	}

	/** Get QtyPickingEnt.
		@return Cantidad de unidades pickeadas expresadas en unidad de medida 
	  */
	public BigDecimal getQtyPickingEnt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyPickingEnt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set UomMultiplyRate.
		@param UomMultiplyRate 
		Factor de conversión de multiplicación entre unidades de medida
	  */
	public void setUomMultiplyRate (BigDecimal UomMultiplyRate)
	{
		set_Value (COLUMNNAME_UomMultiplyRate, UomMultiplyRate);
	}

	/** Get UomMultiplyRate.
		@return Factor de conversión de multiplicación entre unidades de medida
	  */
	public BigDecimal getUomMultiplyRate () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_UomMultiplyRate);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	public I_Z_AsignaTrLog getZ_AsignaTrLog() throws RuntimeException
    {
		return (I_Z_AsignaTrLog)MTable.get(getCtx(), I_Z_AsignaTrLog.Table_Name)
			.getPO(getZ_AsignaTrLog_ID(), get_TrxName());	}

	/** Set Z_AsignaTrLog ID.
		@param Z_AsignaTrLog_ID Z_AsignaTrLog ID	  */
	public void setZ_AsignaTrLog_ID (int Z_AsignaTrLog_ID)
	{
		if (Z_AsignaTrLog_ID < 1) 
			set_Value (COLUMNNAME_Z_AsignaTrLog_ID, null);
		else 
			set_Value (COLUMNNAME_Z_AsignaTrLog_ID, Integer.valueOf(Z_AsignaTrLog_ID));
	}

	/** Get Z_AsignaTrLog ID.
		@return Z_AsignaTrLog ID	  */
	public int getZ_AsignaTrLog_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Z_AsignaTrLog_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_Z_PickingBPView getZ_PickingBPView() throws RuntimeException
    {
		return (I_Z_PickingBPView)MTable.get(getCtx(), I_Z_PickingBPView.Table_Name)
			.getPO(getZ_PickingBPView_ID(), get_TrxName());	}

	/** Set Z_PickingBPView ID.
		@param Z_PickingBPView_ID Z_PickingBPView ID	  */
	public void setZ_PickingBPView_ID (int Z_PickingBPView_ID)
	{
		if (Z_PickingBPView_ID < 1) 
			set_Value (COLUMNNAME_Z_PickingBPView_ID, null);
		else 
			set_Value (COLUMNNAME_Z_PickingBPView_ID, Integer.valueOf(Z_PickingBPView_ID));
	}

	/** Get Z_PickingBPView ID.
		@return Z_PickingBPView ID	  */
	public int getZ_PickingBPView_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Z_PickingBPView_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Z_PickingLin ID.
		@param Z_PickingLin_ID Z_PickingLin ID	  */
	public void setZ_PickingLin_ID (int Z_PickingLin_ID)
	{
		if (Z_PickingLin_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_Z_PickingLin_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_Z_PickingLin_ID, Integer.valueOf(Z_PickingLin_ID));
	}

	/** Get Z_PickingLin ID.
		@return Z_PickingLin ID	  */
	public int getZ_PickingLin_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Z_PickingLin_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_Z_PickingProd getZ_PickingProd() throws RuntimeException
    {
		return (I_Z_PickingProd)MTable.get(getCtx(), I_Z_PickingProd.Table_Name)
			.getPO(getZ_PickingProd_ID(), get_TrxName());	}

	/** Set Z_PickingProd ID.
		@param Z_PickingProd_ID Z_PickingProd ID	  */
	public void setZ_PickingProd_ID (int Z_PickingProd_ID)
	{
		if (Z_PickingProd_ID < 1) 
			set_Value (COLUMNNAME_Z_PickingProd_ID, null);
		else 
			set_Value (COLUMNNAME_Z_PickingProd_ID, Integer.valueOf(Z_PickingProd_ID));
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

	/** Set Z_ReservaVta ID.
		@param Z_ReservaVta_ID Z_ReservaVta ID	  */
	public void setZ_ReservaVta_ID (int Z_ReservaVta_ID)
	{
		if (Z_ReservaVta_ID < 1) 
			set_Value (COLUMNNAME_Z_ReservaVta_ID, null);
		else 
			set_Value (COLUMNNAME_Z_ReservaVta_ID, Integer.valueOf(Z_ReservaVta_ID));
	}

	/** Get Z_ReservaVta ID.
		@return Z_ReservaVta ID	  */
	public int getZ_ReservaVta_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Z_ReservaVta_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Z_ReservaVtaLin ID.
		@param Z_ReservaVtaLin_ID Z_ReservaVtaLin ID	  */
	public void setZ_ReservaVtaLin_ID (int Z_ReservaVtaLin_ID)
	{
		if (Z_ReservaVtaLin_ID < 1) 
			set_Value (COLUMNNAME_Z_ReservaVtaLin_ID, null);
		else 
			set_Value (COLUMNNAME_Z_ReservaVtaLin_ID, Integer.valueOf(Z_ReservaVtaLin_ID));
	}

	/** Get Z_ReservaVtaLin ID.
		@return Z_ReservaVtaLin ID	  */
	public int getZ_ReservaVtaLin_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Z_ReservaVtaLin_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}