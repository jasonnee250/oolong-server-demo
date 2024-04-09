package org.oolong.entity.props;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author: J.N
 * @Date 2023/9/29 23:02
 * @Version 1.0
 */
@Getter @Setter
public class DescriptionProps {
    Position position;
    Size size;
    FillProps fill;
    BorderProps border;
    float angle;
    ConnectorProps connectorProps;
    List<ConnectorDO> connector;
}
