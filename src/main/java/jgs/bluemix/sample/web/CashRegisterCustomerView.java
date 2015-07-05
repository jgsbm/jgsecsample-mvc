package jgs.bluemix.sample.web;

import lombok.Data;

import java.io.Serializable;

/**
 * レジ画面で表示するユーザ情報を保持するDTOです.
 *
 * @author ryozo
 */
@Data
public class CashRegisterCustomerView implements Serializable {
    private String name;
    private String address;
    private String tel;
    private String maskedCreditno;
}
