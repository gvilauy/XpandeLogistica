package org.xpande.logistica.process;

import org.compiere.process.SvrProcess;
import org.xpande.logistica.model.MZMassiveInv;

/**
 * Product: Adempiere ERP & CRM Smart Business Solution. Localization : Uruguay - Xpande
 * Xpande. Created by Gabriel Vila on 6/22/21.
 */
public class ObtenerInfoMassiveInv extends SvrProcess {

    MZMassiveInv massiveInv = null;

    @Override
    protected void prepare() {
        this.massiveInv = new MZMassiveInv(getCtx(), this.getRecord_ID(), get_TrxName());
    }

    @Override
    protected String doIt() throws Exception {

        String message = this.massiveInv.getData();

        if (message != null){
            return "@Error@ " + message;
        }
        return "OK";
    }
}
