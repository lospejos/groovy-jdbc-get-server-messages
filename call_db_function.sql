set client_min_messages = debug;
select * from testme(param => 'Hello');

select * from testme(param => 'Hello', raise_error => true);