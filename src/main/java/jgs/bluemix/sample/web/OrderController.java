package jgs.bluemix.sample.web;

import jgs.bluemix.sample.cache.ReviewCache;
import jgs.bluemix.sample.crypto.HexEncodingTextEncryptor;
import jgs.bluemix.sample.entity.Customer;
import jgs.bluemix.sample.entity.LoginUser;
import jgs.bluemix.sample.entity.Product;
import jgs.bluemix.sample.entity.Review;
import jgs.bluemix.sample.exception.OutOfStockException;
import jgs.bluemix.sample.service.CustomerService;
import jgs.bluemix.sample.service.OrderService;
import jgs.bluemix.sample.service.ProductService;
import jgs.bluemix.sample.service.ReviewService;
import jgs.bluemix.sample.util.CreditUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * 注文操作と関連する機能を提供するControllerです.
 *
 * @author ryozo
 */
@Controller
public class OrderController {

    @Autowired
    ProductService productService;

    @Autowired
    CustomerService customerService;

    @Autowired
    OrderService orderService;

    @Autowired
    ReviewService reviewService;

    @Autowired
    ReviewCache reviewCache;

    @Autowired
    MessageSource messageSource;

    @Autowired
    HexEncodingTextEncryptor hexEncodingTextEncryptor;

    @ModelAttribute
    OrderForm initForm() {
        OrderForm form = new OrderForm();
        form.setQuantity(1);
        return form;
    }

    @RequestMapping("/detail")
    public String detail(@RequestParam(value = "productCode") String productCode, Model model) {
        Product product = productService.findStockProductByProductCode(productCode);
        // Cacheの取得を行う。cacheが存在しない場合はサービス経由でReviewを取得し、キャッシュに反映する。
        List<Review> reviews = reviewCache.get(productCode);
        if (reviews == null) {
            reviews = reviewService.findReviewByProductCode(productCode);
            // 結果が0件であった場合も空であった結果をCacheする.
            reviewCache.put(productCode, reviews);
        }
        model.addAttribute("product", product);
        model.addAttribute("reviews", createProductReviewView(reviews));
        return "detail";
    }

    @RequestMapping("/register")
    public String register(@RequestParam(value = "productCode") String productCode,
                           @AuthenticationPrincipal LoginUser user, Model model) {
        // Modelの情報を再取得してレジ画面を表示する
        Product product = productService.findStockProductByProductCode(productCode);
        Customer customer = customerService.findCustomerWithCreditByMail(user.getUsername());
        CashRegisterCustomerView customerView = createCustomerView(customer);
        model.addAttribute("product", product);
        model.addAttribute("customerView", customerView);
        return "register";
    }

    @RequestMapping("/completion")
    public String completion(@Validated OrderForm form, @RequestParam(value = "productCode") String productCode,
                             UriComponentsBuilder uriBuilder) {
        orderService.order(productCode, form.getQuantity());
        uriBuilder.path("/menu");
        uriBuilder.queryParam("orderedCode", productCode);
        return "redirect:" + uriBuilder.build().toUriString();
    }

    @ExceptionHandler(OutOfStockException.class)
    public ModelAndView handleException(OutOfStockException exception, Locale locale) {
        Product outOfStockProduct = productService.findProductByProductCode(exception.getProductCode());
        ModelAndView mav = new ModelAndView("/detail");
        mav.addObject("hasErrorMessage", Boolean.TRUE);
        mav.addObject("errorMessage", messageSource.getMessage(exception.getMessageCode().getCode(),
                new String[]{outOfStockProduct.getProductName()}, locale));
        mav.addObject(outOfStockProduct);
        return mav;
    }

    private List<ProductReviewView> createProductReviewView(List<Review> reviews) {
        // JDK7のためStreamAPIは利用しない.
        List<ProductReviewView> resultList = new ArrayList<>();
        for (Review review : reviews) {
            resultList.add(convertProductReviewView(review));
        }
        return resultList;
    }

    private ProductReviewView convertProductReviewView(Review review) {
        ProductReviewView result = new ProductReviewView();
        result.setName(review.getCustomer().getCustomerName().charAt(0) + "***");
        result.setEvaluation(review.getEvaluation());
        result.setComment(review.getComment());
        return result;
    }

    private CashRegisterCustomerView createCustomerView(Customer customer) {
        CashRegisterCustomerView result = new CashRegisterCustomerView();
        result.setName(customer.getCustomerName());
        result.setAddress(customer.getAddress());
        result.setTel(customer.getTel());
        String creditNo = hexEncodingTextEncryptor.decrypt(customer.getCreditCard().getEncryptedCreditno());
        result.setMaskedCreditno(CreditUtils.separateAndMask(creditNo));

        return result;
    }
}

