package org.thorn.code.common.identity;

import junit.framework.TestCase;
import org.junit.Assert;

/**
 * TODO.
 *
 * @author chenyun.chris
 * @since 1.0
 */
public class IdNumberTest extends TestCase {

    public void testIsIdNumber() throws Exception {

        String[] ids = {
                "130205199005318070","130205199005313253","130205199005317318",
                "130205199005312437","21148119990101599x","130205199005317319",
                "130205199005314931","13020519900531493x"
        };

        boolean[] res = {true,true,true,true,true,false,false,false};

        for(int i=0;i<ids.length;i++) {
            IdNumber idNumber = new IdNumber(ids[i]);

            Assert.assertEquals(idNumber.isIdNumber(), res[i]);

            if(res[i]) {
                System.out.println(ids[i] + ":" + idNumber.isIdNumber());

                System.out.println(idNumber.getAddress() + ":" + idNumber.getBirthday() + ":" + idNumber.getGender());
            }


        }



    }
}
