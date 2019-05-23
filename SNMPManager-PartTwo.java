/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snmpmanager;

/**
 *
 * @author Wala
 */
import java.io.IOException;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.Target;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;
public class SNMPManager {

    /**
     * @param args the command line arguments
     */
    Snmp snmp = null;
String address = null;

public SNMPManager(String add)
{
address = add;

}
    public static void main(String[] args) throws IOException {
          /**
* Port 161 is used for Read and Other operations
* Port 162 is used for the trap generation
*/
        SNMPManager client = new SNMPManager("udp:127.0.0.1/161");
        client.start();
        /**
* OID - .1.3.6.1.2.1.1.1.0 => SysDec
* OID - .1.3.6.1.2.1.1.5.0 => SysName
* => MIB explorer will be useful here, as discussed in previous article
*/
        System.out.println("------------------------------------------- System Group | SNMP HW Part Two | Wala Ayad -------------------------------------------------- \r\n");
String sysDescr = client.getAsString(new OID(".1.3.6.1.2.1.1.1.0"));
System.out.println("-----------------------------------------------------------System Description ----------------------------------------------------- \r\n");
System.out.println(" "+sysDescr+" \r\n");
System.out.println("-----------------------------------------------------------System Name ------------------------------------------------------------- \r\n");
String SysName = client.getAsString(new OID(".1.3.6.1.2.1.1.5.0"));
System.out.println(" " +SysName + "\r\n");

String SysObjectID = client.getAsString(new OID(".1.3.6.1.2.1.1.2.0"));
System.out.println("-----------------------------------------------------------System Object ID --------------------------------------------------------- \r\n");
System.out.println(" " +SysObjectID + "\r\n");

System.out.println("----------------------------------------------------------- Time UP ------------------------------------------------------------------ \r\n");
String UpTime = client.getAsString(new OID(".1.3.6.1.2.1.1.3.0"));
System.out.println(" " +UpTime + "\r\n");

String Contact = client.getAsString(new OID(".1.3.6.1.2.1.1.4.0"));
System.out.println("----------------------------------------------------------- Contact --------------------------------------------------------- \r\n");
System.out.println(" " +Contact +"\r\n");

String Location = client.getAsString(new OID(".1.3.6.1.2.1.1.6.0"));
System.out.println("----------------------------------------------------------- Location  --------------------------------------------------------- \r\n");
System.out.println("" +Location+ "\r\n");

String Service = client.getAsString(new OID(".1.3.6.1.2.1.1.7.0"));
System.out.println("----------------------------------------------------------- Service --------------------------------------------------------- \r\n");
System.out.println("" +Service);
System.out.println("--------------------------------------------------------- END OF PART TWO -----------------------------------------------------------");








}
    /**
* Start the SNMP session. If you forget the listen() method you will not
* get any answers because the communication is asynchronous
* and the listen() method listens for answers.
* @throws IOException
*/
    


private void start()  throws IOException{
    TransportMapping transport = new DefaultUdpTransportMapping();
     snmp = new Snmp(transport);
// Do not forget this line!
transport.listen();
 //To change body of generated methods, choose Tools | Templates.
    }
/**
* Method which takes a single OID and returns the response from the agent as a String.
* @param oid
* @return
* @throws IOException
*/


    private String getAsString(OID oid) throws IOException {
    ResponseEvent event = get(new OID[] { oid });
    return event.getResponse().get(0).getVariable().toString();
    }
    /**
* This method is capable of handling multiple OIDs
* @param oids
* @return
* @throws IOException
*/


    private ResponseEvent get(OID[] oids) throws IOException {
         //To change body of generated methods, choose Tools | Templates.
        PDU pdu = new PDU();
for (OID oid : oids) {
pdu.add(new VariableBinding(oid));
}

pdu.setType(PDU.GET);
ResponseEvent event = snmp.send(pdu, getTarget(), null);
if(event != null) {
return event;
}

throw new RuntimeException("GET timed out");

}

    private Target getTarget() {
        //To change body of generated methods, choose Tools | Templates.
        Address targetAddress = GenericAddress.parse(address);
CommunityTarget target = new CommunityTarget();
target.setCommunity(new OctetString("SNMP"));
target.setAddress(targetAddress);
target.setRetries(2);
target.setTimeout(1500);
target.setVersion(SnmpConstants.version2c);
return target;

    }

    }
    

