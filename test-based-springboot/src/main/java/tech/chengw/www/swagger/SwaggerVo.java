package tech.chengw.www.swagger;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/12/1
 **/
@Data
@Accessors(chain = true)
@ApiModel("添加设备组VO")
public class SwaggerVo {
    @ApiModelProperty(value = "设备组名称",required = true,example = "客厅灯组")
    @NotBlank(message = "设备组名称不能为空")
    private String deviceGroupName;
    @ApiModelProperty(value = "设备组类型",required = true,example = "1")
    @NotNull(message = "设备类型不能为null")
    private Integer deviceType;
    @ApiModelProperty(value = "设备Id列表",required = true,example = "[1,2,3,4]")
    @NotEmpty(message = "设备不能为空")
    private List<Integer> deviceIds;
}
