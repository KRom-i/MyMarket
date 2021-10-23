package com.gb.market.utils;

import com.gb.market.entities.market.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class ParamsPage {

    private Integer page;
    private Integer size;
    private String title;
    private String min;
    private String max;


    public Integer getPage () {
        return page;
    }

    public void setPage (Integer page) {
        if (isNull (page)) {
            page = 1;
        }
        this.page = page;
    }

    public Integer getSize () {
        return size;
    }

    public void setSize (Integer size) {
        if (isNull (size)) {
            size = 30;
        }
        this.size = size;
    }

    public String getTitle () {
        return title;
    }

    public void setTitle (String title) {
        this.title = checkParam(title);
    }


    public String getMin () {
        return min;
    }

    public void setMin (String min) {
        this.min = checkParam (min);
    }

    public String getMax () {
        return max;
    }

    public void setMax (String max) {
        this.max = checkParam (max);
    }

    public Specification<Product> getSpecification () {

        Specification<Product> specification = Specification.where (null);

        if (!isNull (title)){
            specification = specification.and (titleContains ());
        }

        if (!isNull (min)){
            specification = specification.and (priceGreaterThanOrEq ());
        }

        if (!isNull (max)){
            specification = specification.and (priceLesserThanOrEq ());
        }

        return specification;
    }

    private Specification<Product> titleContains() {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("title"), "%" + title + "%");
    }

    private Specification<Product> priceGreaterThanOrEq() {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.greaterThanOrEqualTo(root.get("price"), min);
        };
    }

    private Specification<Product> priceLesserThanOrEq() {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.lessThanOrEqualTo(root.get("price"), max);
        };
    }

    private String checkParam (String param) {
        if (!isNull (param) && param.length () > 0){
            return param;
        }
        return null;
    }

    public String getParams(){

        StringBuilder sb = new StringBuilder ();

        if (!isNull (title)){
            sb.append ("&title=").append (title);
        }

        if (!isNull (min)){
            sb.append ("&min=").append (min);
        }

        if (!isNull (max)){
            sb.append ("&max=").append (max);
        }

        return sb.toString ();
    }
}
