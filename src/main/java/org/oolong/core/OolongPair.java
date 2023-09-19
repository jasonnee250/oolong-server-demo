package org.oolong.core;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: J.N
 * @Date 2023/9/20 00:13
 * @Version 1.0
 */
@Setter @Getter
public class OolongPair<T,V> {
    T t;
    V v;
}
