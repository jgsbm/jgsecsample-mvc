package jgs.bluemix.sample.repository;

import jgs.bluemix.sample.entity.CreditCard;
import jgs.bluemix.sample.entity.Customer;
import jgs.bluemix.sample.persistence.customer.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 顧客関連の操作を提供するRepositoryです.
 *
 * @author ryozo
 */
@Repository
public class CustomerRepository {

    @Autowired
    CustomerMapper customerMapper;

    /**
     * 引数に指定されたメールアドレスを保持するユーザ（クレジットカード情報無し）を検索します.
     * クレジットカード情報が必要である場合は{@link #findCustomerWithCreditByMail(String)}を利用してください.
     * @param email 検索条件とするメールアドレス
     * @return クレジットカード情報を保持しないCustomerエンティティ
     */
    public Customer findCustomerByMail(String email) {
        return customerMapper.findCustomerByMail(email);
    }

    /**
     * 引数に指定されたメールアドレスを保持するユーザ情報（クレジットカード情報付き）を検索します.
     * クレジットカード情報が不要である場合は{@link #findCustomerByMail(String)}を利用してください.
     * @param email 検索条件とするメールアドレス
     * @return クレジットカード情報を保持するCustomerエンティティ
     */
    public Customer findCustomerWithCreditByMail(String email) {
        return customerMapper.findCustomerWithCreditByMail(email);
    }

    /**
     * 引数に指定されたクレジットカードを追加します.
     * @param creditCard 追加対象クレジットカード情報
     */
    public void insertCreditCard(CreditCard creditCard) {
        customerMapper.insertCreditCard(creditCard);
    }

    /**
     * 引数に指定されたユーザを追加します.
     * @param customer 追加対象ユーザ
     */
    public void insertCustomer(Customer customer) {
        customerMapper.insertCustomer(customer);
    }
    
    /**
     * 引数に指定されたユーザを更新します.
     * @param customer 更新対象ユーザ
     */
    public void updateCustomer(Customer customer) {
        customerMapper.updateCustomer(customer);
    }
    
    /**
     * 引数に指定されたクレジットカード更新します.
     * @param creditCard 更新対象クレジットカード情報
     */
    public void updateCreditCard(CreditCard creditCard) {
        customerMapper.updateCreditCard(creditCard);
    }
    
    /**
     * 引数に指定されたユーザのパスワードを更新します.
     * @param customer 更新対象ユーザ
     */
    public void updatePassword(Customer customer) {
        customerMapper.updatePassword(customer);
    }

}
