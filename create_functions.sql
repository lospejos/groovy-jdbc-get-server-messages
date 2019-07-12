create or replace function testme (
    param varchar
)
returns varchar
as
$$
declare
begin
    raise debug 'Debug message. Param value is: %', param;
    raise notice 'Notice message. Param value is: %', param;
    raise warning 'Warning message. Param value is: %', param;
    return concat('Response on ', param);
end
$$ language plpgsql;