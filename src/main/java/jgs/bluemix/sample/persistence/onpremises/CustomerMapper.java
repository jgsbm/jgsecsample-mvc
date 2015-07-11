package jgs.bluemix.sample.persistence.onpremises;

import jgs.bluemix.sample.entity.CreditCard;
import jgs.bluemix.sample.entity.Customer;

/**
 * Customerテーブルに関する操作を提供するMapperクラスです.
 *
 * @author ryozo
 */
public interface CustomerMapper {

    /**
     * 引数に指定されたメールアドレスを保持するユーザを検索します.
     * 当メソッドはCustomersテーブルのみを検索対象とし、クレジットカード情報の検索は行いません.
     * クレジットカード情報が必要となる場合は{@link #findCustomerWithCreditByMail(String)}メソッドを利用してください.
     * @param email 検索条件とするメールアドレス
     * @return クレジットカード情報を含まないCustomerエンティティ
     */
    Customer findCustomerByMail(String email);

    /**
     * 引数に指定されたメールアドレスを保持するユーザとクレジットカード情報を検索します.
     * 当メソッドの呼び出し結果の{@link Customer}には当該ユーザの{@link CreditCard}エンティティが設定された状態で返却されます.
     * クレジットカード情報が不要である場合{@link #findCustomerByMail(String)}メソッドを利用してください.
     * @param email
     * @return クレジットカード情報を含むCustomerエンティティ
     */
    Customer findCustomerWithCreditByMail(String email);

    /**
     * 引数に指定されたユーザを追加します.
     * @param customer 追加対象ユーザ
     */
    void insertCustomer(Customer customer);

    /**
     * 引数に指定されたクレジットカードを追加します.
     * @param creditCard 追加対象クレジットカード情報
     */
    void insertCreditCard(CreditCard creditCard);
    
    /**
     * 引数に指定されたユーザを更新します.
     * @param customer 更新対象ユーザ
     */
    void updateCustomer(Customer customer);
    
    /**
     * 引数に指定されたクレジットカードを更新します.
     * @param creditCard 更新対象クレジットカード情報
     */
    void updateCreditCard(CreditCard creditCard);
    
    /**
     * 引数に指定されたユーザのパスワードを更新します.
     * @param customer 更新対象ユーザ
     */
    void updatePassword(Customer customer);
}
