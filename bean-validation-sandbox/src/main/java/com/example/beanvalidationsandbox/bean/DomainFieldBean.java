package com.example.beanvalidationsandbox.bean;

import com.example.beanvalidationsandbox.annotation.Size;
import com.example.beanvalidationsandbox.domain.CollectionDomain;
import com.example.beanvalidationsandbox.domain.IntDomain;
import com.example.beanvalidationsandbox.domain.StringDomain;
import lombok.Data;

@Data
public class DomainFieldBean {

    @Size(min = 5, max = 10)
    private StringDomain stringDomain;

    @Size(max = 50)
    private IntDomain intDomain;

    @Size(max = 1)
    private CollectionDomain collectionDomain;

}
