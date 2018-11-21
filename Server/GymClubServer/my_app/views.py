from django.shortcuts import render, HttpResponse
from django.contrib.auth import authenticate
from .models import MyUser, Coach, Course
from .forms import LoginForm, RegisterForm
from . import jsons as Jsons
from django.core.serializers.json import json

import re
import os
import mimetypes
from wsgiref.util import FileWrapper
from django.http import StreamingHttpResponse

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


def coach_admin(request):
    if request.method == "POST":
        coach_name = request.POST.get("coach_name", None)
        coach_birthday = request.POST.get("coach_birthday", None)
        coach_title = request.POST.get("coach_title", None)
        coach_intro = request.POST.get("coach_intro", None)
        coach_signature = request.POST.get("coach_signature", None)
        stu_num = request.POST.get("stu_num", None)
        heading_img = request.POST.get("heading_img", None)
        other_img = request.POST.get("other_img", None)
        have_course = request.POST.get("have_course", None)
        course_type = request.POST.get("course_type", None)

        try:
            Coach.objects.create(
                coach_name=coach_name,
                coach_birthday=coach_birthday,
                coach_title=coach_title,
                coach_intro=coach_intro,
                coach_signature=coach_signature,
                student_num=stu_num,
                coach_head_img=heading_img,
                coach_extra_img=other_img,
                coach_have_course=have_course,
                coach_course_type=course_type,
            )
            return render(request, "coach-admin.html", {"result": "添加成功！"})
        except Exception as e:
            return render(request, "coach-admin.html", {"result": "添加失败！" + str(e)})
    else:
        return render(request, "coach-admin.html")


def file_iterator(file_name, chunk_size=8192, offset=0, length=None):
    with open(file_name, "rb") as f:
        f.seek(offset, os.SEEK_SET)
        remaining = length
        while True:
            bytes_length = chunk_size if remaining is None else min(remaining, chunk_size)
            data = f.read(bytes_length)
            if not data:
                break
            if remaining:
                remaining -= len(data)
            yield data


def video(request):
    return render(request, "video.html")


def stream_video(request):
    resp = None
    try :
        path = "F:/Python_Projects/Django Projects/GymClubServer/static/videos/demo.mp4"
        """将视频文件以流媒体的方式响应"""
        range_header = request.META.get('HTTP_RANGE', '').strip()
        range_re = re.compile(r'bytes\s*=\s*(\d+)\s*-\s*(\d*)', re.I)
        range_match = range_re.match(range_header)
        size = os.path.getsize(path)
        content_type, encoding = mimetypes.guess_type(path)
        content_type = content_type or 'application/octet-stream'
        if range_match:
            first_byte, last_byte = range_match.groups()
            first_byte = int(first_byte) if first_byte else 0
            last_byte = first_byte + 1024 * 1024 * 8       # 8M 每片,响应体最大体积
            if last_byte >= size:
                last_byte = size - 1
            length = last_byte - first_byte + 1
            resp = StreamingHttpResponse(file_iterator(path, offset=first_byte, length=length),
                                         status=206, content_type=content_type)
            resp['Content-Length'] = str(length)
            resp['Content-Range'] = 'bytes %s-%s/%s' % (first_byte, last_byte, size)
        else:
            # 不是以视频流方式的获取时，以生成器方式返回整个文件，节省内存
            resp = StreamingHttpResponse(FileWrapper(open(path, 'rb')), content_type=content_type)
            resp['Content-Length'] = str(size)
    finally:
        resp['Accept-Ranges'] = 'bytes'
        return resp
