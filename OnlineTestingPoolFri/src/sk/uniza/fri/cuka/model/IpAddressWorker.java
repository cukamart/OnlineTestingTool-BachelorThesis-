package sk.uniza.fri.cuka.model;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.stereotype.Component;

@Component
public class IpAddressWorker {

	public long ipToLong(InetAddress ip) {
		byte[] octets = ip.getAddress();
		long result = 0;

		for (byte octet : octets) {
			result <<= 8;
			result |= octet & 0xff;
		}
		return result;
	}

	public boolean isInRange(InetAddress ipLo, InetAddress ipHi) throws UnknownHostException {
		long ipMin = ipToLong(ipLo);
		long ipMax = ipToLong(ipHi);
		long currentIp = ipToLong(getCurrentIpAddress());

		return currentIp >= ipMin && currentIp <= ipMax;
	}

	public InetAddress getCurrentIpAddress() throws UnknownHostException {
		return InetAddress.getLocalHost();
	}
}
