/**
 * 
 */
package learn;

/**
 * @author Kyle
 *
 */
public class App {
	
	
	public long add(long[] operands) {
		long ret = 0;
		if(operands == null) {
			throw new IllegalArgumentException("Operands cannot be null");
		}
		if(operands.length == 0) {
			throw new IllegalArgumentException("Operands cannot be empty");
		}
		for(long num: operands) {
			ret += num;
		}
		
		
		return ret;
	}
}
