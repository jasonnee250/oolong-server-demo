package org.oolong.entity.props;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author: J.N
 * @Date 2023/9/29 22:57
 * @Version 1.0
 */
@Getter @Setter
public class ConnectorDO {
    List<String> ids;
    Position pos;
    String type;
}
