from django.db import models
from django.contrib.auth import get_user_model
from django.contrib.auth.models import (
    BaseUserManager, AbstractBaseUser
)


# MyUserManager overloading
class MyUserManager(BaseUserManager):
    def create_user(self, email, password):
        user = self.model(
            email=self.normalize_email(email),
            nickname=email
        )
        user.set_password(password)
        user.save(self._db)
        return user

    def set_nickname(self, nickname=None):
        user = self.model(
            nickname=nickname,
        )
        user.save(self._db)
        return user


# Custom user model
class MyUser(AbstractBaseUser):
    email = models.EmailField(
        verbose_name='email address',
        max_length=255,
        unique=True,
    )
    nickname = models.CharField(max_length=100, default="")
    creation_date = models.DateTimeField(auto_now_add=True)

    objects = MyUserManager()

    USERNAME_FIELD = 'email'
    REQUIRED_FIELDS = []


# 教练
class Coach(models.Model):
    # 教练id
    coach_id = models.CharField(null=False, max_length=255,)
    # 教练名字
    coach_name = models.CharField(max_length=255, default="",)
    # 教练生日
    coach_birthday = models.DateField(default="")
    # 教练简介
    coach_intro = models.CharField(max_length=600, default="")
    # 个性签名
    coach_signature = models.CharField(max_length=100, default="")
    # 教练名
    coach_title = models.CharField(max_length=255, default="")
    # 学生人数
    student_num = models.CharField(max_length=255, default="0")
    # 教练头像url
    coach_head_img = models.CharField(max_length=255, default="")
    # 教练其他头像
    coach_extra_img = models.CharField(max_length=255, default="")
    # 教练已开课程
    coach_have_course = models.CharField(max_length=255, default="",)
    # 教练主要面向的课程类型
    coach_course_type = models.CharField(max_length=255, default="",)


# 课程
class Course(models.Model):
    # 课程id
    course_id = models.CharField(null=False, max_length=255)
    # 课程名
    course_name = models.CharField(max_length=255, null=False)
    # 课程简介
    course_intro = models.CharField(max_length=600, default="")
    # 课程训练部位
    course_training_part = models.CharField(max_length=100, default="")
    # 课程头像
    course_head_img = models.CharField(max_length=255, default="")
    # 课程更多头像
    course_extra_img = models.CharField(max_length=255, default="")
