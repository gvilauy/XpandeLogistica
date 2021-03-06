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

/** Generated Model for Z_MassiveInvLine
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_Z_MassiveInvLine extends PO implements I_Z_MassiveInvLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210622L;

    /** Standard Constructor */
    public X_Z_MassiveInvLine (Properties ctx, int Z_MassiveInvLine_ID, String trxName)
    {
      super (ctx, Z_MassiveInvLine_ID, trxName);
      /** if (Z_MassiveInvLine_ID == 0)
        {
			setC_BPartner_ID (0);
			setC_BPartner_Location_ID (0);
			setC_Currency_ID (0);
			setC_Order_ID (0);
			setDateOrdered (new Timestamp( System.currentTimeMillis() ));
			setDateReserved (new Timestamp( System.currentTimeMillis() ));
			setIsSelected (false);
// N
			setTotalAmt (Env.ZERO);
			setZ_MassiveInv_ID (0);
			setZ_MassiveInvLine_ID (0);
			setZ_Picking_ID (0);
			setZ_ReservaVta_ID (0);
        } */
    }

    /** Load Constructor */
    public X_Z_MassiveInvLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_Z_MassiveInvLine[")
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
			set_Value (COLUMNNAME_C_BPartner_Location_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_Location_ID, Integer.valueOf(C_BPartner_Location_ID));
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

	public I_C_Currency getC_Currency() throws RuntimeException
    {
		return (I_C_Currency)MTable.get(getCtx(), I_C_Currency.Table_Name)
			.getPO(getC_Currency_ID(), get_TrxName());	}

	/** Set Currency.
		@param C_Currency_ID 
		The Currency for this record
	  */
	public void setC_Currency_ID (int C_Currency_ID)
	{
		if (C_Currency_ID < 1) 
			set_Value (COLUMNNAME_C_Currency_ID, null);
		else 
			set_Value (COLUMNNAME_C_Currency_ID, Integer.valueOf(C_Currency_ID));
	}

	/** Get Currency.
		@return The Currency for this record
	  */
	public int getC_Currency_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Currency_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
			set_Value (COLUMNNAME_C_Order_ID, null);
		else 
			set_Value (COLUMNNAME_C_Order_ID, Integer.valueOf(C_Order_ID));
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

	public I_C_SalesRegion getC_SalesRegion() throws RuntimeException
    {
		return (I_C_SalesRegion)MTable.get(getCtx(), I_C_SalesRegion.Table_Name)
			.getPO(getC_SalesRegion_ID(), get_TrxName());	}

	/** Set Sales Region.
		@param C_SalesRegion_ID 
		Sales coverage region
	  */
	public void setC_SalesRegion_ID (int C_SalesRegion_ID)
	{
		if (C_SalesRegion_ID < 1) 
			set_Value (COLUMNNAME_C_SalesRegion_ID, null);
		else 
			set_Value (COLUMNNAME_C_SalesRegion_ID, Integer.valueOf(C_SalesRegion_ID));
	}

	/** Get Sales Region.
		@return Sales coverage region
	  */
	public int getC_SalesRegion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_SalesRegion_ID);
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

	/** Set Selected.
		@param IsSelected Selected	  */
	public void setIsSelected (boolean IsSelected)
	{
		set_Value (COLUMNNAME_IsSelected, Boolean.valueOf(IsSelected));
	}

	/** Get Selected.
		@return Selected	  */
	public boolean isSelected () 
	{
		Object oo = get_Value(COLUMNNAME_IsSelected);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public I_AD_User getSalesRep() throws RuntimeException
    {
		return (I_AD_User)MTable.get(getCtx(), I_AD_User.Table_Name)
			.getPO(getSalesRep_ID(), get_TrxName());	}

	/** Set Sales Representative.
		@param SalesRep_ID 
		Sales Representative or Company Agent
	  */
	public void setSalesRep_ID (int SalesRep_ID)
	{
		if (SalesRep_ID < 1) 
			set_Value (COLUMNNAME_SalesRep_ID, null);
		else 
			set_Value (COLUMNNAME_SalesRep_ID, Integer.valueOf(SalesRep_ID));
	}

	/** Get Sales Representative.
		@return Sales Representative or Company Agent
	  */
	public int getSalesRep_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SalesRep_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Total Amount.
		@param TotalAmt 
		Total Amount
	  */
	public void setTotalAmt (BigDecimal TotalAmt)
	{
		set_Value (COLUMNNAME_TotalAmt, TotalAmt);
	}

	/** Get Total Amount.
		@return Total Amount
	  */
	public BigDecimal getTotalAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TotalAmt);
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

	public I_Z_MassiveInv getZ_MassiveInv() throws RuntimeException
    {
		return (I_Z_MassiveInv)MTable.get(getCtx(), I_Z_MassiveInv.Table_Name)
			.getPO(getZ_MassiveInv_ID(), get_TrxName());	}

	/** Set Z_MassiveInv ID.
		@param Z_MassiveInv_ID Z_MassiveInv ID	  */
	public void setZ_MassiveInv_ID (int Z_MassiveInv_ID)
	{
		if (Z_MassiveInv_ID < 1) 
			set_Value (COLUMNNAME_Z_MassiveInv_ID, null);
		else 
			set_Value (COLUMNNAME_Z_MassiveInv_ID, Integer.valueOf(Z_MassiveInv_ID));
	}

	/** Get Z_MassiveInv ID.
		@return Z_MassiveInv ID	  */
	public int getZ_MassiveInv_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Z_MassiveInv_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Z_MassiveInvLine ID.
		@param Z_MassiveInvLine_ID Z_MassiveInvLine ID	  */
	public void setZ_MassiveInvLine_ID (int Z_MassiveInvLine_ID)
	{
		if (Z_MassiveInvLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_Z_MassiveInvLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_Z_MassiveInvLine_ID, Integer.valueOf(Z_MassiveInvLine_ID));
	}

	/** Get Z_MassiveInvLine ID.
		@return Z_MassiveInvLine ID	  */
	public int getZ_MassiveInvLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Z_MassiveInvLine_ID);
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
}