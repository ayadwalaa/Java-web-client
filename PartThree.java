/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snmpagent1;

import java.io.File;
import org.snmp4j.agent.mo.MOAccessImpl;
import org.snmp4j.agent.mo.MOScalar;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.Variable;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.snmp4j.TransportMapping;
import org.snmp4j.agent.BaseAgent;
import org.snmp4j.agent.CommandProcessor;
import org.snmp4j.agent.DuplicateRegistrationException;
import org.snmp4j.agent.MOGroup;
import org.snmp4j.agent.ManagedObject;
import org.snmp4j.agent.mo.MOTableRow;
import org.snmp4j.agent.mo.snmp.RowStatus;
import org.snmp4j.agent.mo.snmp.SnmpCommunityMIB;
import org.snmp4j.agent.mo.snmp.SnmpNotificationMIB;
import org.snmp4j.agent.mo.snmp.SnmpTargetMIB;
import org.snmp4j.agent.mo.snmp.StorageType;
import org.snmp4j.agent.mo.snmp.VacmMIB;
import org.snmp4j.agent.security.MutableVACM;
import org.snmp4j.mp.MPv3;
import org.snmp4j.security.SecurityLevel;
import org.snmp4j.security.SecurityModel;
import org.snmp4j.security.USM;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.TimeTicks;
import org.snmp4j.smi.Variable;
import org.snmp4j.transport.TransportMappings;

/**
 *
 * @author Noor
 */
public class SnmpAgent1 extends BaseAgent {
     private String address;
     private static OID sysDesc = new OID(".1.3.6.1.2.1.1.1.0");
     private static OID sysObjID = new OID(".1.3.6.1.2.1.1.2.0");
     private static OID sysUptime = new OID(".1.3.6.1.2.1.1.3.0");
     private static OID sysCont = new OID(".1.3.6.1.2.1.1.4.0");
     private static OID sysName = new OID(".1.3.6.1.2.1.1.5.0");
     private static OID sysLoc = new OID(".1.3.6.1.2.1.1.6.0");
     private static OID sysServ = new OID(".1.3.6.1.2.1.1.7.0");
     
     
     


    public SnmpAgent1(String Address){
        /**
44
         * Creates a base agent with boot-counter, config file, and a
45
         * CommandProcessor for processing SNMP requests. Parameters:
46
         * "bootCounterFile" - a file with serialized boot-counter information
47
         * (read/write). If the file does not exist it is created on shutdown of
48
         * the agent. "configFile" - a file with serialized configuration
49
         * information (read/write). If the file does not exist it is created on
50
         * shutdown of the agent. "commandProcessor" - the CommandProcessor
51
         * instance that handles the SNMP requests.
52
         */

        super(new File("conf.agent"), new File("bootCounter.agent"),
        new CommandProcessor(
        new OctetString(MPv3.createLocalEngineID())));
        this.address = Address;

    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            SnmpAgent1 snmpAg = new SnmpAgent1("0.0.0.0/4444");
            snmpAg.start();
            // Since BaseAgent registers some MIBs by default we need to unregister
            // one before we register our own sysDescr. Normally you would
            // override that method and register the MIBs that you need
            snmpAg.unregisterManagedObjects(snmpAg.getSnmpv2MIB());
            // Register a system description, use one from you product environment
            // to test withasus
            snmpAg.registerManagedObjects((SnmpAgent1.createReadOnly(sysDesc,"Hp Elite Book 850 for Wala Ayad")));
            snmpAg.registerManagedObjects((SnmpAgent1.createReadOnly(sysObjID,"11610930")));
            snmpAg.registerManagedObjects((SnmpAgent1.createReadOnly(sysUptime,new Timestamp(System.currentTimeMillis()).toString())));
            snmpAg.registerManagedObjects((SnmpAgent1.createReadOnly(sysCont,"none")));
            snmpAg.registerManagedObjects((SnmpAgent1.createReadOnly(sysName," Wala Ayad")));
            snmpAg.registerManagedObjects((SnmpAgent1.createReadOnly(sysLoc,"none")));
            snmpAg.registerManagedObjects((SnmpAgent1.createReadOnly(sysServ,Integer.toString(75))));
            while(true){
            }// waaaaaaait

        } catch (Exception ex) {
            Logger.getLogger(SnmpAgent1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //@Override
    public void registerManagedObjects(ManagedObject mo) {
        try {
            server.register(mo, null);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

    
    public void unregisterManagedObjects(MOGroup moGroup) {
        moGroup.unregisterMOs(server, getContext(moGroup));

    }

    @Override
    protected void addUsmUser(USM usm) {
        
    }

    @Override
    protected void addNotificationTargets(SnmpTargetMIB stmib, SnmpNotificationMIB snmib) {
        
    }

    @Override
    protected void addViews(VacmMIB vmib) {
           vmib.addGroup(SecurityModel.SECURITY_MODEL_SNMPv2c, new OctetString(
                "cpublic"), new OctetString("v1v2group"),
                StorageType.nonVolatile);
        vmib.addAccess(new OctetString("v1v2group"), new OctetString("public"),
                SecurityModel.SECURITY_MODEL_ANY, SecurityLevel.NOAUTH_NOPRIV,
                MutableVACM.VACM_MATCH_EXACT, new OctetString("fullReadView"),
                new OctetString("fullWriteView"), new OctetString(
                        "fullNotifyView"), StorageType.nonVolatile);
 
        vmib.addViewTreeFamily(new OctetString("fullReadView"), new OID("1.3"),
                new OctetString(), VacmMIB.vacmViewIncluded,
                StorageType.nonVolatile);

    }

    @Override
    protected void addCommunities(SnmpCommunityMIB scmib) {
        Variable[] com2sec = new Variable[] { new OctetString("public"),
                new OctetString("cpublic"), // security name
                getAgent().getContextEngineID(), // local engine ID
                new OctetString("public"), // default context name
                new OctetString(), // transport tag
                new Integer32(StorageType.nonVolatile), // storage type
                new Integer32(RowStatus.active) // row status
        };
        scmib.getSnmpCommunityEntry().addRow(scmib.getSnmpCommunityEntry().createRow(new OctetString("public2public").toSubIndex(true), com2sec));
 
    }

    @Override
    protected void registerManagedObjects() {
        
    }

    @Override
    protected void unregisterManagedObjects() {
        
    }
    protected void initTransportMappings() throws IOException {
        transportMappings = new TransportMapping[1];
        Address addr = GenericAddress.parse(address);
        TransportMapping tm = TransportMappings.getInstance().createTransportMapping(addr);
        transportMappings[0] = tm;
    }
    public void start() throws IOException {
        init();
        // This method reads some old config from a file and causes
        // unexpected behavior.
        // loadConfig(ImportModes.REPLACE_CREATE);
        addShutdownHook();
        getServer().addContext(new OctetString("public"));
        finishInit();
        run();
        sendColdStartNotification();
    }
     public  static MOScalar createReadOnly(OID oid,Object value ){
        return new MOScalar(oid,
        MOAccessImpl.ACCESS_READ_ONLY,
        getVariable(value));
    }
 //; this is ur code ! the exact code!
    private static Variable getVariable(Object value) {
        if(value instanceof String) {
            return new OctetString((String)value);
        }
        else if(value instanceof OID){
            return new OID((OID) value);
        }
        else if (value instanceof Integer32){
            return new Integer32(((Integer32) value).toInt());
           
        }
        else if(value instanceof TimeTicks){
            return new TimeTicks((TimeTicks)value);
        }
        throw new IllegalArgumentException("Unmanaged Type: " + value.getClass());
    }
}
    

