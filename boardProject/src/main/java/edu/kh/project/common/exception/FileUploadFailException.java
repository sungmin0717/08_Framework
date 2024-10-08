package edu.kh.project.common.exception;

//사용자 정의 예외 만드는 방법
// -> 아무 Exception클래스를 상성복ㅓ
// 촏찯ㅇ ㄸㅌㅊ데샤ㅐㅜ tkdthr -> 촏찯ㅇ 사용자 ㅈ
public class FileUploadFailException extends RuntimeException{

	
	public FileUploadFailException() {
		super("파일 업로드 실패");
	}
	
	public FileUploadFailException(String message) {
		super(message);
	}
}
