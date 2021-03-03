package tech.chengw.www.swagger;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/12/1
 **/
@Api(tags = "设备组API")
@RestController
public class SwaggerController {
    @ApiOperation("在用户账号下添加设备组")
    @PostMapping(value = "/app/deviceGroup/add")
    public ResponseMessage<List<Integer>> addDeviceGroup(@ApiParam(value = "添加设备组的参数", required = true) @Validated @RequestBody SwaggerVo addDeviceGroupVO) {
        List<Integer> failDeviceIds = new ArrayList<>();
        if (!failDeviceIds.isEmpty()) {
            return new ResponseMessage<>("BusinessException.CODE_DEVICES_GROUP_BIND_FAIL", failDeviceIds);
        }
        return new ResponseMessage<>(ResponseMessage.CODE_SUCCESS);
    }

}
