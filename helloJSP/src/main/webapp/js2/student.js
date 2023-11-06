//std.js

import svc from './service.js';

//페이지 로딩되면서 바로 실행되는 코드
//비동기방식코드 ->순차적 가독성 높이기.async,await.사용해보기..

//async가 함수()앞에 선언 await는 async안에서만
//async function(
//await 처리. (promise객체 반환)
//await
//await
//)
//svc.studentList(function(result)=>{},function(err){});//아래처럼 화살표함수로 나타낼수있다

//학생목록 출력
svc.studentList( //성공후 실행함수
	result => {
		console.log(result)
		let tbody = document.querySelector('#list');
		result.forEach(student => {
			tbody.append(makeTr(student));
		})
	},
	//실패후실행함수
	err => console.log('error =>', err)
);

//아래 코드↓를 위↑처럼 만들어 봤다..

// fetch('../studentList.do')
// 	.then(resolve => resolve.json())
// 	.then(result => {
// 		console.log(result)
// 		let tbody = document.querySelector('#list');
// 		result.forEach(student => {
// 			tbody.append(makeTr(student));
// 		})
// 	})
// 	.catch(err => console.log('error=>', err))


//등록버튼 이벤트
//const myButton = document.getElementById("my-button");
document.querySelector('#addBtn').addEventListener('click', addCallback);
//callback함수

//수정버튼 이벤트.서블릿(db변경)->화면변경
document.querySelector('#modBtn').addEventListener('click', modifyCallback);

//callback함수
function addCallback(e) {
	console.log('테스트 버튼')
	//학생아이디 입력값을 찾기
	let sid = document.querySelector('input[name=sid]').value;//쿼리셀렉 or 겟엘리먼트 사용
	let sname = document.querySelector('input[name=sname]').value;
	let pass = document.querySelector('input[name=pass]').value;
	let sdept = document.querySelector('select[name=sdept]').value;
	let birth = document.querySelector('input[name=birth]').value;

	let param = `id=${sid}&name=${sname}&password=${pass}&dept=${sdept}&birthday=${birth}`;
	console.log(param);

	//ajax 호출
	//get : url 패턴.값의 제한이 있다..
	//post : 파라미터 표현X, 값의제한X, content-type 지정.
	//fetch('../addStudent.do?'+ param) = get방식 요청 
	//fetch('../addStudent.do', {
	//		method: 'post',   //주소창에 비밀번호 표시 방지
	//		headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
	//		body: param
	//})
	//		.then(resolve => resolve.json())
	//	.then(result => {
	//		if (result.retCode == 'OK') {
	//			alert('성공')
	//			let tr = makeTr({ studentId: sid, studentName: sname, studentBirthday: birth });
	//			document.querySelector('#list').append(tr);//화면(밑에 나오는 표)에 추가하기
	//		} else {
	//			alert('실패')
	//		}
	//	})
	//	.catch(err => console.log('error=>', err));
}

//모달 보여주기
function modifyCallback(e) {
	let id = document.querySelector('.modal-body input[name=sid]').value;
	let pass = document.querySelector('.modal-body input[name=pass]').value;
	let name = document.querySelector('.modal-body input[name=name]').value;
	let birth = document.querySelector('.modal-body input[name=birth]').value;
	let param = `id=${id}&name=${name}&password=${pass}&birthday=${birth}`;

	svc.editStudent(
		// 1) optObj
		{
			method: 'post',
			headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
			body: param
		},
		// 2) success
		result => {
			console.log(result);
			if (result.retCode == 'OK') {
				alert('수정 성공');
				let trTags = document.getElementById('list').querySelectorAll('tr');
				trTags.forEach(obj => {
					if (obj.childNodes[0].innerText == id) {
						obj.childNodes[1].innerHTML = name;
						obj.childNodes[2].innerHTML = birth;
					}
					//3) eroorCall

				});
			} else {
				alert('수정 실패');
			}
		},
		err => console.log('err', err)
		);
}
//end of modifycallback


//등록되거나 삭제되면 화면에 표시되게
function makeTr(obj) {
	let showFields = ['studentId', 'studentName', 'studentBirthday']
	let tr = document.createElement('tr');

	tr.setAttribute('data-sid', obj.studentId);
	tr.addEventListener('dblclick', showModal);

	for (let prop in obj) {
		if (showFields.indexOf(prop) == -1) {
			continue;
		}
		let td = document.createElement('td')
		td.innerHTML = obj[prop];
		tr.append(td);
	}
	//삭제버튼(td안에넣어서).
	let td = document.createElement('td');
	let btn = document.createElement('button');
	btn.setAttribute('data-sid', obj.studentId);
	btn.innerHTML = '삭제';
	btn.addEventListener('click', function(e) {
		//ajax 호출.->servlet실행한다는 의미
		svc.removeStudent(obj.studentId,
			result => {
				if (result.retCode == 'OK') {
					alert('삭제성공')
					tr.remove();
				} else {
					alert('삭제실패')
				}
			},
			err => console.log('error: ', err)
		);
		td.append(btn);
		tr.append(td);

		return tr;
	}
}



function showModal(e) {
	console.log(e.target.parentElement, this);
	let id = this.children[0].innerHTML;
	id = this.dataset.sid;//'data-sid':std1
	console.log(id);


	var modal = document.getElementById("myModal");
	// Get the <span> element that closes the modal
	var span = document.getElementsByClassName("close")[0];


	// When the user clicks on <span> (x), close the modal
	span.onclick = function() {
		modal.style.display = "none";
	}

	// When the user clicks anywhere outside of the modal, close it
	window.onclick = function(event) {
		if (event.target == modal) {
			modal.style.display = "none";
		}
	}
	svc.getStudent(id,
		result => {
			modal.style.display = "block";


			modal.querySelector('h2').innerHTML = result.vo.studentName;
			document.querySelector('.modal-body input[name=sid]').value = result.vo.studentId;
			document.querySelector('.modal-body input[name=name]').value = result.vo.studentName;
			document.querySelector('.modal-body input[name=pass]').value = result.vo.studentPassword;
			document.querySelector('.modal-body input[name=birth]').value = result.vo.studentBirthday;


			err => console.log('error=>', err)
		})

	// Get the modal
}