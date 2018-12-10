from .models import MyUser
import re


# 登录
class LoginForm:
    def __init__(self, request):
        self.username = request.POST.get('username', None)
        self.password = request.POST.get('password', None)
        self.error_msg = None

    def check_username(self):
        if not self.username:
            self.error_msg = "用户名不能为空！"
            return False
        else:
            try:
                MyUser.objects.get(email=self.username)
                return True
            except MyUser.DoesNotExist:
                self.error_msg = "用户不存在！"
                return False

    def check_password(self):
        if not self.password:
            self.error_msg = "密码不能为空！"
            return False
        else:
            return True

    def is_valid(self):
        if self.check_password():
            if self.check_username():
                return True
            else:
                return False
        else:
            return False

    def get_error_msg(self):
        return self.error_msg

    def get_username(self):
        return self.username

    def get_password(self):
        return self.password


# 注册
class RegisterForm:
    def __init__(self, request):
        self.username = request.POST.get("username", None)
        self.password = request.POST.get("password", None)
        self.password_cfm = request.POST.get("password_cfm", None)
        self.error_msg = None

    def check_username(self):
        if not self.username:
            self.error_msg = "用户名不能为空！"
            return False
        else:
            for ch in self.username:
                if u'\u4e00' <= ch <= u'\u9fff':
                    self.error_msg = "用户名包含非法字符！"
                    return False
            str_reg = r'^[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+){0,4}@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+){0,4}$'
            if not re.match(str_reg, self.username):
                self.error_msg = "当前仅支持邮箱注册！"
                return False
            # 检查账户是否已存在
            have_user = MyUser.objects.filter(email=self.username).count()
            if have_user > 0:
                self.error_msg = "该用户已存在！"
                return False
            return True

    def check_password(self):
        if not self.password:
            self.error_msg = "密码不能为空！"
            return False
        elif len(self.password) < 6:
            self.error_msg = "密码长度不能小于6个字符！"
            return False
        elif self.password != self.password_cfm:
            self.error_msg = "两次密码不匹配！"
            return False
        else:
            for ch in self.username:
                if u'\u4e00' <= ch <= u'\u9fff':
                    self.error_msg = "用户名包含非法字符！"
                    return False
        return True

    def is_valid(self):
        if self.check_username():
            if self.check_password():
                return True
            else:
                return False
        else:
            return False

    def get_error_msg(self):
        return self.error_msg

    def get_username(self):
        return self.username

    def get_password(self):
        return self.password
