package jgs.bluemix.sample.service;

import jgs.bluemix.sample.entity.Customer;
import jgs.bluemix.sample.exception.AlreadyUserRegistedException;
import jgs.bluemix.sample.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 顧客関連のビジネスロジックを提供するサービスです.
 *
 * @author ryozo
 */
@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    /**
     * 指定されたユーザの情報をクレジットカード情報付きで取得します.
     *
     * @param email 取得対象顧客のメールアドレス
     * @return クレジットカード情報付きのCustomerエンティティ
     */
    public Customer findCustomerWithCreditByMail(String email) {
        return customerRepository.findCustomerWithCreditByMail(email);
    }

    /**
     * 引数のユーザを新規顧客として登録します.
     *
     * @param customer 新規顧客
     * @return 登録結果(登録時に発行される各種情報を含むCsutomerインスタンス)
     */
    @Transactional
    public Customer signup(Customer customer) {
        try {
            customerRepository.insertCustomer(customer);
            customerRepository.insertCreditCard(customer.getCreditCard());
        } catch (DuplicateKeyException e) {
            // UserIDの重複(既に登録済のユーザ)
            throw new AlreadyUserRegistedException(e);
        }
        return customer;
    }

}
