package sk.uniza.fri.cuka.test.tests.model;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import sk.uniza.fri.cuka.model.IpAddressWorker;

/**
 * 
 * Parametrizovany test testujuci prevod IP adresy na long skusa previest minimalnu IP adresu a maximalnu
 */
@RunWith(Parameterized.class)
public class IpAddressWorkerTest {
	
	private InetAddress inetAddress;
	private long ipToLong;

	public IpAddressWorkerTest(InetAddress inetAddress, long ipToLong) {
		this.inetAddress = inetAddress;
		this.ipToLong = ipToLong;
	}
	
	@Parameters
	public static Collection<Object[]> data() throws UnknownHostException {
		InetAddress addr = InetAddress.getByName("127.0.0.1");
		InetAddress addr2 = InetAddress.getByName("127.255.255.255");
		
		return Arrays.asList(new Object[][] { {addr, 2130706433}, {addr2, 2147483647} });
	}

	@Test
	public void ipToLongTest() throws UnknownHostException {
		IpAddressWorker ip = new IpAddressWorker();
		
		long result = ip.ipToLong(inetAddress);
		
		Assert.assertEquals(ipToLong, result);
	}
}
