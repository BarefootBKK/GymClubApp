from django.core.serializers.json import json
from .models import Coach,Course


def get_json(code, data):
    return json.dumps({"code": code, "data": data}, ensure_ascii=False)


def get_coaches():
    data = []
    coach_list = Coach.objects.all()
    for item in coach_list:
        data_item = dict()
        data_item['coachName'] = item.coach_name
        data_item['coachBirthday'] = str(item.coach_birthday)
        data_item['coachIntro'] = item.coach_intro
        data_item['coachSignature'] = item.coach_signature
        data_item['coachTitle'] = item.coach_title
        data_item['coachHeadImg'] = item.coach_head_img
        data_item['coachExtraImg'] = item.coach_extra_img
        data_item['coachHaveCourse'] = item.coach_have_course
        data_item['coachCourseType'] = item.coach_course_type
        data_item['studentNum'] = item.student_num
        data.append(data_item)
    return data


def get_course():
    data = []
    course_list = Course.objects.all()
    for item in course_list:
        data_item = dict()
        data_item['courseName'] = item.course_name
        data_item['courseIntro'] = item.course_intro
        data_item['courseTrainingPart'] = item.course_training_part
        data_item['courseHeadImg'] = item.course_head_img
        data_item['coursePoster'] = item.course_extra_img
        data.append(data_item)
    return data