package jp.tokyo.leon.hera.rest.controller.admin;

import jp.tokyo.leon.hera.common.api.ResponseResult;
import jp.tokyo.leon.hera.dao.entity.admin.User;
import jp.tokyo.leon.hera.rest.annotation.ApiOperationLog;
import jp.tokyo.leon.hera.service.admin.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author leon
 * @date 2024/2/12 21:38
 */
@RestController
@RequestMapping("/v2/api/admin/users")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    @ApiOperationLog("通过ID获取用户")
    public ResponseResult<User> getUserById(@PathVariable Long id) {
        return ResponseResult.ok(userService.getUserById(id));
    }

    @GetMapping("/hello")
    public ResponseResult<String> hello() {
        return ResponseResult.ok("hello");
    }
}
