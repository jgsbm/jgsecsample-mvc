package jgs.bluemix.sample.util;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

/**
 * {@link CreditUtils}クラスのテストクラスです
 */
@RunWith(Enclosed.class)
public class CreditUtilsTest {

    /**
     * {@link CreditUtils#mask(String)}のテストを行います.
     */
    public static class maskのテスト {
        @Test
        public void maskに16桁の区切り済文字を渡す() {
            // exercise
            String result = CreditUtils.mask("1234-5678-9012-3456");

            // verify
            assertThat(result, is("XXXX-XXXX-XXXX-3456"));
        }

        @Test
        public void maskに15桁の区切り済文字を渡す() {
            // exercise
            String result = CreditUtils.mask("1234-5678-9012-345");

            // verify
            assertThat(result, is("XXXX-XXXX-XXXX-345"));
        }

        @Test
        public void maskに14桁の区切り済文字を渡す() {
            // exercise
            String result = CreditUtils.mask("1234-5678-9012-34");

            // verify
            assertThat(result, is("XXXX-XXXX-XXXX-34"));
        }

        @Test
        public void 変則的な区切りのカード番号を渡しても4桁でマスクされる() {
            // exercise
            String result = CreditUtils.mask("1234-567890-1234");

            // verify
            assertThat(result, is("XXXX-XXXX-1234"));
        }

        @Test
        public void nullや空文字はそのまま返る() {
            assertThat(CreditUtils.mask(null), is(nullValue()));
            assertThat(CreditUtils.mask("") , is(""));
        }
    }

    /**
     * {@link CreditUtils#separate(String)}のテストを行います.
     */
    public static class separateのテスト {
        @Test
        public void separateに16桁のクレカ番号を渡す() {
            // exercise
            String result = CreditUtils.separate("1234567890123456");

            // verify
            assertThat(result, is("1234-5678-9012-3456"));
        }

        @Test
        public void separateに15桁のクレカ番号を渡す() {
            // exercise
            String result = CreditUtils.separate("123456789012345");

            // verify
            assertThat(result, is("1234-5678-9012-345"));
        }

        @Test
        public void separateに14桁のクレカ番号を渡す() {
            // exercise
            String result = CreditUtils.separate("12345678901234");

            // verify
            assertThat(result, is("1234-5678-9012-34"));
        }


        @Test
        public void nullや空文字はそのまま返る() {
            assertThat(CreditUtils.separate(null), is(nullValue()));
            assertThat(CreditUtils.separate("") , is(""));
        }
    }
}
