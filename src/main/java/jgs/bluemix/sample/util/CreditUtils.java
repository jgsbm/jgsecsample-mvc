package jgs.bluemix.sample.util;

import org.springframework.util.StringUtils;

/**
 * クレジットカード関連の操作を行うUtilityです.
 *
 * @author ryozo
 */
public class CreditUtils {

    /** クレジットカードの区切り文字 */
    public static final String CARD_DELIMITER = "-";

    /**
     * クレジットカード番号をマスキングします.
     * {@link #CARD_DELIMITER}で区切られたクレジット番号のうち、最終ブロック以外を全て"X"埋めします.
     * 当メソッドの引数となるクレジットカード番号は所定桁数毎に{@link #CARD_DELIMITER}で区切られている必要があります.
     * なお、当サンプルは簡単のためにマスキング部は4桁固定としています.
     *
     * <pre>
     *     1234-5678-9012-3456 → XXXX-XXXX-XXXX-3456
     *     1234-5678-9012-34   → XXXX-XXXX-XXXX-34
     *     1234-567890-12345   → XXXX-XXXX-12345
     * </pre>
     *
     * @param creditno 対象のクレジットカード番号（{@link #CARD_DELIMITER}で区切り済）
     * @return マスキングされたカード番号
     */
    public static String mask(String creditno) {
        if (!StringUtils.hasLength(creditno)) {
            return creditno;
        }
        // JDK7のためStreamAPIは利用しない
        String[] creditNoBlock = creditno.split(CARD_DELIMITER);
        StringBuilder builder = new StringBuilder(creditno.length());
        for (int i = 0; i < creditNoBlock.length - 1; i++) {
            builder.append("XXXX").append(CARD_DELIMITER);
        }
        builder.append(creditNoBlock[creditNoBlock.length - 1]);
        return builder.toString();
    }

    /**
     * クレジットカード番号の区切りとマスキングを同時に実施します.
     * 当サンプルでは簡単のためにクレジット会社によるカード番号の桁数違いを意識しません.
     *
     * @see #separate(String)
     * @see #mask(String)
     * @param creditno
     * @return
     */
    public static String separateAndMask(String creditno) {
        return mask(separate(creditno));
    }

    /**
     * クレジットカード番号を4桁毎に区切り文字({@link #CARD_DELIMITER}で区切ります.
     * 当サンプルでは、簡単のためにクレジット会社によるカード番号の桁数違いを意識せず、一律4桁で分割を行います.
     *
     * <pre>
     *     1234567890123456 → 1234-5678-9012-3456
     *     123456789012345  → 1234-5678-9012-345
     *     12345678901234   → 1234-5678-9012-34
     * </pre>
     *
     * @param creditno 対象のクレジット番号
     * @return 変換済のクレジット番号
     */
    public static String separate(String creditno) {
        if (!StringUtils.hasLength(creditno)) {
            return creditno;
        }
        StringBuilder builder = new StringBuilder();
        // Java7のため、StreamAPIは利用しない
        int currentIndex = 0;
        for (int i = 4; i < creditno.length(); i = i + 4) {
            builder.append(creditno.substring(currentIndex, i)).append("-");
            currentIndex = i;
        }
        builder.append(creditno.substring(currentIndex));
        return builder.toString();
    }
}
