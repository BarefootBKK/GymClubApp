from django.shortcuts import render, HttpResponse
from django.contrib.auth import authenticate
from .models import MyUser, Coach, Course
from .forms import LoginForm, RegisterForm
from . import jsons as Jsons
from django.core.serializers.json import json

my_content_type = 'application/json; charset=utf-8'


# Create your views here.
def login(request):
    if request.method == "POST":
        form = LoginForm(request)
        if form.is_valid():
            user = authenticate(username=form.get_username(), password=form.get_password())
            if user is not None:
                current_user = MyUser.objects.get(email=form.get_username())
                user_info = {"username": current_user.email,
                             "nickname": current_user.nickname}
                return HttpResponse(Jsons.get_json(200, user_info), content_type=my_content_type)
            else:
                return HttpResponse(Jsons.get_json(201, {"error_msg": "密码错误！"}),
                                    content_type=my_content_type)
        else:
            return HttpResponse(Jsons.get_json(201, {"error_msg": form.get_error_msg()}),
                                content_type=my_content_type)
    else:
        return HttpResponse("This is GET in login.")


def register(request):
    if request.method == "POST":
        form = RegisterForm(request)
        if form.is_valid():
            MyUser.objects.create_user(form.get_username(), form.get_password())
            return HttpResponse(Jsons.get_json(200, "注册成功！"), content_type=my_content_type)
        else:
            return HttpResponse(Jsons.get_json(202, {"error_msg": form.get_error_msg()}),
                                content_type=my_content_type)
    else:
        return HttpResponse("This is GET in register.")


def coach(request):
    if request.method == "GET":
        return HttpResponse(Jsons.get_json(200, Jsons.get_coaches()), content_type=my_content_type)
    else:
        return HttpResponse("This is POST in coach")
