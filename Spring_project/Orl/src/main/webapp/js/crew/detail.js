//el 들어간 거는 js로 빼면 오류남 ? el표현된 것이 서버에서 돌아야하니까..
//어떻게 해결? el 표현으로 넘기지말고 html로 표현해서 넣으면 가능 - val() 이나 text() 사용하는 등..
//js src 하는 위에서 js 변수로 잡아도 되는데, idx를 노출해도 되면 이렇게 하면되고, 노출하면 안되는 정보일 경우 x

// 토글처리,, 클래스에 번호일일히 붙이지 않는 방법 -> on 으로 위에 태그로 잡아서 처리
// 부트 (el -> timeleaf)

$(document).ready(function () {

	crewInfoShowing();

	commentList();

	//When click the 'body' tag toggle that menu(active-inactive).
	$('body').click(function () {
		var matches = document.querySelectorAll('.active');
		$('body').click(function () {
			for (var i = 0; i < matches.length; i++) {
				matches[i].classList.toggle('active');
			}
		});
	});

	//Comment insert.
	$('#submit').click(function () {
		$.ajax({
			url: url + '/crew/commentInsert',
			type: 'post',
			data: {
				crewIdx: crewIdx,
				crewComment: $('#crewComment').val(),
				memberIdx: memberIdx
			},
			success: function (data) {
				if (data == 0) {
					alert('로그인 여부를 확인해주세요.');
				}
				commentList();
			}
		})
	});

	//Joining the crew. 
	$('#joinToCrew').on('click', 'button', function () {
		if (memberIdx == '') {
			window.location.href = "/orl/member/login?referer=/orl/crew/detail?crewIdx=" + crewIdx + "";
		} else {
			$.ajax({
				url: url + '/crew/joinToCrewMemberList',
				type: 'get',
				data: {
					memberIdx: memberIdx,
					crewIdx: crewIdx
				},
				success: function (data) {
					if (data == 0) {
						alert('가입에 실패했습니다.');
					} else if (data == 1) {
						alert('크루에 가입하셨습니다.');
						window.location.href = "" + url2 + "/crew/detail?crewIdx=" + crewIdx + "";
					}
				}
			});
		}
	});

	//Leaving the crew.
	$('#outFromCrew').on('click', 'button', function () {
		if (memberIdx == '') {
			window.location.href = "/orl/member/login?referer=/orl/crew/detail?crewIdx=" + crewIdx + "";
		} else {
			$.ajax({
				url: url + '/crew/deleteCrewMemberFromList',
				type: 'get',
				data: {
					memberIdx: memberIdx,
					crewIdx: crewIdx
				},
				success: function (data) {
					if (data == 0) {
						alert('탈퇴에 실패했습니다.');
					} else if (data == 1) {
						alert('해당 크루를 탈퇴했습니다.');
						window.location.href = "" + url2 + "/crew/detail?crewIdx=" + crewIdx + "";
					}
				}
			});
		}
	});

});//document ready end.

function crewInfoShowing() {
	$.ajax({
		url: url + '/crew/getCrewInfo',
		type: 'get',
		data: {
			memberIdx: memberIdx,
			crewIdx: crewIdx
		},
		success: function (data) {
			let crewImg = '<img src="' + url2 + '/images/crew/' + data.crewPhoto + '" class="card-img-top" alt="..." id="cardImg">';
			$('#crew-img').html(crewImg);
			let crewTitle = data.crewName;
			$('#crew-title').html(crewTitle);

			//Crew-manage button
			if (data.memberIdx == memberIdx) {
				let crewMng = '<a href="' + url2 + '/crew/edit/' + data.crewIdx + '" class="btn btn-sm color_blue text_bold">크루 관리</a>';
				$('#crew-Mng').html(crewMng);
			}

			let crewIntro = '<pre class="card-text">' + data.crewDiscription + '</pre>';
			$('#crew-introduction').html(crewIntro);

			//Hash-Tag
			if (data.crewTag != null) {
				let str = [];
				const crewTagArr = data.crewTag;
				str = crewTagArr.split(",");
				var crewHashTag = "";
				for (var idx = 0; idx < str.length; idx++) {
					crewHashTag += '<li class="tag-item">#' + str[idx] + '</li>';
				}
				$("#crewHashTag").html(crewHashTag);
			}

			let crewCap = '';
			if (memberIdx != '') {
				crewCap += '<a href="' + url2 + '/feed/userfeed/' + data.memberIdx + '">';
			}
			crewCap += '<p class="text_bold">' + data.memberNickName + '</p>';
			crewCap += '<img id="profile" src="' + url2 + '/images/member/profile/' + data.memberProfile + '">';
			if (memberIdx != '') {
				crewCap += '</a>';
			}
			$('#crewCap').html(crewCap);

			let crewMemberNum = '<p class="text_bold">크루원</p>';
			crewMemberNum += '<p>' + data.crewMemberNum + '</p>';
			$('#crew_number').html(crewMemberNum);

			let crewCommentNum = '<p class="text_bold">댓글</p>';
			crewCommentNum += '<p>' + data.crewCommentNum + '</p>';
			$('#crew_comment_number').html(crewCommentNum);

			let crewOut = document.querySelector('#outFromCrew');
			let crewJoin = document.querySelector('#joinToCrew');
			if (!data.isReg) {
				crewOut.classList.add('display-hidden');
			} else if (data.isReg && memberIdx != data.memberIdx) {
				crewJoin.classList.add('display-hidden');
			}

			let crewCommentText = document.querySelector('#crewComment');
			if(!data.isReg){
				crewCommentText.readOnly = true;
			} else {
				crewCommentText.readOnly = false;
			}
		}
	});
}

//Comment~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//Comment toggle managing.
function commentToggle(parameter) {
	const toggleMenu = document.querySelector('.commentMenu' + parameter + '');
	toggleMenu.classList.toggle('active');
	var html = '<li><a href="javascript:editComment(' + parameter + ')">수정</a></li>';
	html += '<li><a href="javascript:deleteComment(' + parameter + ')">삭제</a>';
	$('#commentMenu' + parameter + '').html(html);
}

//Comment edit.
function editComment(parameter) {
	$.ajax({
		url: url + '/crew/getCommentInfo',
		type: 'GET',
		data: { crewCommentIdx: parameter },
		success: function (data) {
			var html = '<td><img id="profile" src="' + url2 + '/images/member/profile/' + data.memberProfile + '"></td>';
			html += '<td><p id="nickname">' + data.memberNickName + '</p>';
			html += '<input class="form-control" id="newCrewComment" type="text" value="' + data.crewComment + '"></td>';
			html += '<td><p><br></p><a href="javascript:commentList()" class="tag-item">취소</a>';
			html += '<a href="javascript:updateComment(' + parameter + ')" class="tag-item">등록</a></td>';
			$('#' + parameter + '').html(html);
		}
	});
}
function updateComment(parameter) {
	$.ajax({
		url: url + '/crew/commentUpdate',
		type: 'post',
		data: {
			crewComment: $('#newCrewComment').val(),
			crewCommentIdx: parameter
		},
		success: function (data) {
			if (data == 0) {
				alert('로그인 여부를 확인해주세요.');
			}
			commentList();
		}
	});
}

//Comment delete.
function deleteComment(parameter) {
	$.ajax({
		url: url + '/crew/commentDelete',
		type: 'GET',
		data: { crewCommentIdx: parameter },
		success: function (data) {
			commentList();
		}
	});
}

//HTML output function.
function commentList(parameter) {
	$.ajax({
		url: url + '/crew/getCommentInfoList',
		type: 'GET',
		data: {
			crewIdx: crewIdx,
			currentPageNum: parameter
		},
		contentType: "application/x-www-form-urlencoded; charset=UTF-8;",
		success: function (data) {
			//Comment없으면 return
			if (data.infoList.length == 0) {
				var html = '<tr><td>아직 작성된 댓글이 없습니다.</td></tr>';
				$('#commentList').html(html);
				return;
			}

			// 있는 경우 Comment 정보 반복문으로 처리
			var html = '';
			var html2 = '';
			$.each(data.infoList, function (index, item) {
				html += '<tr id="' + item.crewCommentIdx + '">';
				html += '<td>';
				if (memberIdx != '') {
					html += '<a href="' + url2 + '/feed/userfeed/' + item.memberIdx + '">';
				}
				html += '<img id="profile" src="' + url2 + '/images/member/profile/' + item.memberProfile + '"></a></td>';
				html += '<td>';
				if (memberIdx != '') {
					html += '<a href="' + url2 + '/feed/userfeed/' + item.memberIdx + '">';
				}
				html += '<p id="nickname">' + item.memberNickName + '</p></a>';
				html += '<p class="content">' + item.crewComment + '</p>';
				html += '<p class="date">' + item.crewCommentDate + '</p></td>';
				html += '<td>';
				if (memberIdx == item.memberIdx) {
					html += '<div class="commentMenuBox">';
					html += '<div class="icon" onclick="commentToggle(' + item.crewCommentIdx + ');">';
					html += '<a href="#"><img id="commentMng" src="' + url2 + '/images/crew/icon.png"></a>';
					html += '<div id="cmid" class="commentMenu commentMenu' + item.crewCommentIdx + '"><ul id="commentMenu' + item.crewCommentIdx + '"></ul></div>'; html += '</div>';
					html += '</div>';
				}
				html += '</td></tr>';
				$('#commentList').html(html);
			});

			//Paging처리
			var prev = parseInt(data.cri.currentPageNum) - 1;
			if (prev == 0) {
				prev = 1;
			}
			var next = parseInt(data.cri.currentPageNum) + 1;
			if (next > data.totalPageNum) {
				next = data.totalPageNum
			}
			html2 += '<li class="page-item">';
			html2 += '<a class="page-link" href="javascript:commentList(' + prev + ')">&lt</a></li>';
			for (var i = 1; i <= data.totalPageNum; i++) {
				html2 += '<li class="page-item">';
				html2 += '<a href="javascript:commentList(' + i + ')" class="page-link">' + i + '</a></li>';
			}
			html2 += '<li class="page-item">';
			html2 += '<a class="page-link" href="javascript:commentList(' + next + ')">&gt</a></li>';
			$('#paging').html(html2);
		}
	});
}