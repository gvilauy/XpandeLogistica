package org.xpande.logistica.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Product: Adempiere ERP & CRM Smart Business Solution. Localization : Uruguay - Xpande
 * Xpande. Created by Gabriel Vila on 6/22/21.
 */
public class MZMassiveInvLine extends X_Z_MassiveInvLine {

    public MZMassiveInvLine(Properties ctx, int Z_MassiveInvLine_ID, String trxName) {
        super(ctx, Z_MassiveInvLine_ID, trxName);
    }

    public MZMassiveInvLine(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
