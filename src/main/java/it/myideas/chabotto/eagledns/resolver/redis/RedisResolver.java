/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.myideas.chabotto.eagledns.resolver.redis;

import org.xbill.DNS.ExtendedFlags;
import org.xbill.DNS.Flags;
import org.xbill.DNS.Header;
import org.xbill.DNS.Message;
import org.xbill.DNS.Name;
import org.xbill.DNS.OPTRecord;
import org.xbill.DNS.Opcode;
import org.xbill.DNS.Rcode;
import org.xbill.DNS.Record;
import org.xbill.DNS.Section;
import org.xbill.DNS.TSIG;
import org.xbill.DNS.TSIGRecord;
import se.unlogic.eagledns.EagleDNS;
import se.unlogic.eagledns.Request;
import se.unlogic.eagledns.SystemInterface;
import se.unlogic.eagledns.plugins.BasePlugin;
import se.unlogic.eagledns.resolvers.Resolver;

/**
 *
 * @author Tommaso Doninelli <tommaso.doninelli@gmail.com>
 */
public class RedisResolver extends BasePlugin implements Resolver {

    @Override
    public Message generateReply(Request request) throws Exception {

        Message query = request.getQuery();
        Record queryRecord = query.getQuestion();

        if (queryRecord == null) {
            return null;
        }

        Name name = queryRecord.getName();

        // Magia ON
        Header header;
        // boolean badversion;
        int flags = 0;

        header = query.getHeader();
        if (header.getFlag(Flags.QR)) {
            return null;
        }
        if (header.getRcode() != Rcode.NOERROR) {
            return null;
        }
        if (header.getOpcode() != Opcode.QUERY) {
            return null;
        }

        TSIGRecord queryTSIG = query.getTSIG();
        TSIG tsig = null;
        if (queryTSIG != null) {
            tsig = systemInterface.getTSIG(queryTSIG.getName());
            if (tsig == null || tsig.verify(query, request.getRawQuery(), request.getRawQueryLength(), null) != Rcode.NOERROR) {
                return null;
            }
        }

        OPTRecord queryOPT = query.getOPT();
        //		if (queryOPT != null && queryOPT.getVersion() > 0) {
        //			// badversion = true;
        //		}

        if (queryOPT != null && (queryOPT.getFlags() & ExtendedFlags.DO) != 0) {
            flags = EagleDNS.FLAG_DNSSECOK;
        }

        Message response = new Message(query.getHeader().getID());
        response.getHeader().setFlag(Flags.QR);
        if (query.getHeader().getFlag(Flags.RD)) {
            response.getHeader().setFlag(Flags.RD);
        }

        response.addRecord(queryRecord, Section.QUESTION);

        int type = queryRecord.getType();
        int dclass = queryRecord.getDClass();

        // Magia Off
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
