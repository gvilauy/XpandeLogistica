package org.xpande.logistica.process;

import org.compiere.process.SvrProcess;
import org.xpande.logistica.model.MZPicking;

/**
 * Product: Adempiere ERP & CRM Smart Business Solution. Localization : Uruguay - Xpande
 * Xpande. Created by Gabriel Vila on 6/15/21.
 */
public class ObtenerInfoPicking extends SvrProcess {

    MZPicking picking = null;

    @Override
    protected void prepare() {
        this.picking = new MZPicking(getCtx(), this.getRecord_ID(), get_TrxName());
    }

    @Override
    protected String doIt() throws Exception {

        String message = this.picking.getData();

        if (message != null){
            return "@Error@ " + message;
        }
        return "OK";
    }
}
