"""GymClubServer URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/2.1/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.urls import path
from django.conf.urls import url
from my_app import views
from django.contrib.staticfiles import views as my_view
from . import settings

urlpatterns = [
    path('admin/', admin.site.urls),
    url(r'^login', views.login),
    url(r'^register', views.register),
    url(r'^course', views.course),
    url(r'^coach', views.coach),
    url(r'^ca', views.coach_admin),
    url(r'^video', views.video),
    url(r'^stream_video', views.stream_video),
    url(r'^test', views.test_connect),
    url(r'^static/(?P<path>.*)$', my_view.serve),
]
