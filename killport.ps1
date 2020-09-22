$processes = (get-NetTCPConnection| ? {$_.LocalPort -eq "8000"}).OwningProcess
foreach ($process in $processes) {Get-Process -PID $process | Stop-Process -Force}
