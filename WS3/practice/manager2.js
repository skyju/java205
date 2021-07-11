// 흐름 위주 복기 -07 08 수업 

function Member(id, pw, name) { // 1. 생성자: 함수 인 것을 기억할 것 (js에는 클래스가 없으므로)
    this.id = id;
    this.pw = pw;
    this.name = name;
};

let memberArray = [];

window.onload = function () {

    if (localStorage.getItem('memberArray')) {
        memberArray = JSON.parse(localStorage.getItem('memberArray')); 
        // 2. JSON 문법
        // GET! : JSON.parse(localStorage.getItem('key값')); -> 문자열을 가져오므로 객체로 변환
        // SET! : localStorage.setItem('key값', JSON.stringify(객체)) -> 객체를 문자열로 반환/
        // localStorage.deleteItem('key값'); -> key값을 참조하여 싹 지움
    }
    setList();

    let id = document.getElementById('id'); // 3. (key, value)인거 까지는 이해, value를 못담는 거 질문
    let pw = document.getElementById('pw');
    let repw = document.getElementById('repw');
    let name = document.getElementById('name');

    document.getElementById('joinMemberArea').onsubmit = function () {
        if (id.value.length < 1 || pw.value.length < 1 || repw.value.length < 1 || name.value.length < 1) {
            alert('필수 입력 사항입니다.');
            return false;
        }
        if (pw.value != repw.value) {
            alert('비밀번호가 일치하지 않습니다.');
            return false;
        }
        memberArray.push(new Member(id.value, pw.value, name.value));
        localStorage.setItem('memberArray', JSON.stringify(memberArray));
        alert('등록 완료');
    }
}

function setList() {
    let tbody = '<tr>';
    if (memberArray.length < 1) {
        tbody += '<td colspan="4">입력 값이 없습니다.</td></tr>';
    } else {
        for (let i = 0; i < memberArray.length; i++) {
        tbody += '<td>'+i+'</td>';
        tbody += '<td>'+memberArray[i].id+'</td>';
        tbody += '<td>'+memberArray[i].pw+'</td>';
        tbody += '<td>'+memberArray[i].name+'</td></tr>'
        }
    }
    document.getElementById('tbody').innerHTML = tbody;
}