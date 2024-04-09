package org.oolong.entity.props;

import lombok.Data;

@Data
public class BorderProps {
    float lineWidth;
    FillProps color;
    LineTypeEnum lineType;
}
