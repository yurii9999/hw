package g244.kuzmin;

import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.LinkedHashSet;

public class KeyCodesEncoder {
    /**
     * Encode set of keys to bytebuffer
     * [contain(UP)? (1/0), ... contain(DIGIT3)? (1/0)]
     * @param codes set of keys
     * @return bytebuffer
     */
    public static ByteBuffer encode(Collection<String> codes) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(size());

        checkEncode(byteBuffer, codes, "UP");
        checkEncode(byteBuffer, codes, "DOWN");
        checkEncode(byteBuffer, codes, "LEFT");
        checkEncode(byteBuffer, codes, "RIGHT");
        checkEncode(byteBuffer, codes, "SPACE");
        checkEncode(byteBuffer, codes, "DIGIT1");
        checkEncode(byteBuffer, codes, "DIGIT2");
        checkEncode(byteBuffer, codes, "DIGIT3");

        byteBuffer.flip();
        return byteBuffer;
    }

    /**
     * Decodes bytebuffer to set of keys
     */
    public static Collection<String> decode(ByteBuffer byteBuffer) {
        Collection<String> codes = new LinkedHashSet();

        checkDecode(byteBuffer, codes, "UP");
        checkDecode(byteBuffer, codes, "DOWN");
        checkDecode(byteBuffer, codes, "LEFT");
        checkDecode(byteBuffer, codes, "RIGHT");
        checkDecode(byteBuffer, codes, "SPACE");
        checkDecode(byteBuffer, codes, "DIGIT1");
        checkDecode(byteBuffer, codes, "DIGIT2");
        checkDecode(byteBuffer, codes, "DIGIT3");

        return codes;
    }

    /** return size of byteBuffer */
    public static int size() {
        return 5 + 3;
    }

    private static void checkEncode(ByteBuffer byteBuffer, Collection<String> codes, String key) {
        if (codes.contains(key)) {
            byteBuffer.put((byte) 1);
        } else {
            byteBuffer.put((byte) 0);
        }
    }

    private static void checkDecode(ByteBuffer byteBuffer, Collection<String> codes, String key) {
        byte b = byteBuffer.get();
        if (b == 1) {
            codes.add(key);
        }
    }
}
