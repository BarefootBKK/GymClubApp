from django.core.serializers.json import json
from .models import Coach


def get_json(code, data):
    return json.dumps({"code": code, "data": data}, ensure_ascii=False)


def get_coaches():
    data = []
    coach_list = Coach.objects.all()
    for item in coach_list:
        data_item = dict()
        data_item['coach_name'] = item.coach_name
        data_item['coach_signature'] = item.coach_signature
        data_item['stu_num'] = item.student_num
        data_item['head_img_url'] = item.coach_head_img
        data.append(data_item)
    return data
