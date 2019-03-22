function pick( pitnumber ) {
	document.getElementById("command").value = "pick";
	document.getElementById("pitnumber").value = pitnumber;
	document.getElementById("formGameBoard").submit();
}
	
function resetGame() {
	document.getElementById("command").value = "reset";
	document.getElementById("pitnumber").value = 0;		
}