local time = redis.call('time')
return time[1] * 1000 + time[2] / 1000
