create or replace function testme (
    param varchar
)
returns varchar
as
$$
declare
begin
    raise notice 'Param value is: %', param;
    return concat('Response on ', param);
end
$$ language plpgsql;