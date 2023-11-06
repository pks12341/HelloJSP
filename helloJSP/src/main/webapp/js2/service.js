/**
 * service.js
 */
//std.js 29행에 있던 코드옮김
//async function studentList() {
//	let req = await fetch('../studentList.do');//studentList처리가 다 끝나면 
//	let json = await req.json(); // {"retCode" : " OK"} -> {retCode :"ON"}//14행이 다끝나면 15행 실행..
//	let tbody = document.querySelector('#list');
//	try {
//		json.forEach(student => {
//			tbody.append(makeTr(student));
//		})
//	} catch (err) {
//		console.log('error =>', err)
//	}
//}





export default {
	//목록처리.
	async studentList(successCallback, errorCallback) { //리스트라는 메서드 만들고
		let req = await fetch('../studentList.do');
		let json = await req.json();
		try {
			successCallback(json);
		} catch (err) {
			errorCallback(err);
		}
	},

	//단건조회
	async getStudent(id, successCallback, errorCallback) {
		let req = await fetch('../getStudent.do?sid=' + id);
		let json = await req.json();
		try {
			successCallback(json);
		} catch (err) {
			errorCallback(err);
		}
	},

	//등록.
	async addStudent(optObj, successCallback, errorCallback) {
		let req = await fetch('../addStudent.do', optObj);
		let json = await req.json();
		try {
			successCallback(json);
		} catch (err) {
			errorCallback(err);
		}
	},

	//수정.
	async editStudent(optObj, successCallback, errorCallback) {
		let req = await fetch('../editStudent.do', optObj);
		let json = await req.json();
		try {
			successCallback(json);
		} catch (err) {
			errorCallback(err);
		}
	},

	//삭제.
	async removeStudent(id, successCallback, errorCallback) {
		let req = await fetch('../delStudent.do', id);
		let json = await req.json();
		try {
			successCallback(json);
		} catch (err) {
			errorCallback(err);
		}
	}
}