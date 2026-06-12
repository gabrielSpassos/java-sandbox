# Unit / Integration / Contract / Mutation Tests POC 

## Integration Test

- Proofs that some functionality works as expected
- Covers happy and unhappy paths
- Boots service, so is slower

## Contract 

- Proofs that the contract (API in this POC) is correct, no change break the contract
- Use mocks, so is fast

## Mutation

- Tests of Tests
  - `./test-mutation.sh`

- First report failed
```
================================================================================
- Statistics
  ================================================================================
>> Line Coverage (for mutated classes only): 167/189 (88%)
>> 37 tests examined
>> Generated 97 mutations Killed 64 (66%)
>> Mutations with no coverage 33. Test strength 100%
>> Ran 72 tests (0.74 tests per mutation)
```
![report-1](resources/report-1.png)

- After tunning
```xml
<configuration>
    <targetClasses>
        <param>com.gabrielspassos.*</param>
    </targetClasses>
    <targetTests>
        <param>com.gabrielspassos.*</param>
    </targetTests>
    <excludedClasses>
        <param>*Entity</param>
    </excludedClasses>
    <outputFormats>
        <param>HTML</param>
    </outputFormats>
    <threads>4</threads>
    <mutationThreshold>80</mutationThreshold>
</configuration>
```

```
================================================================================
- Statistics
================================================================================
>> Line Coverage (for mutated classes only): 117/124 (94%)
>> 37 tests examined
>> Generated 53 mutations Killed 51 (96%)
>> Mutations with no coverage 2. Test strength 100%
>> Ran 59 tests (1.11 tests per mutation)
Enhanced functionality available at https://www.arcmutate.com/

Build messages:- 
* Project uses Spring, but the Arcmutate Spring plugin is not present. (https://docs.arcmutate.com/docs/spring.html)
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```
![report-2](resources/report-2.png)

## TODO Application

> Grocery TODO List system 
- [x] add item 
- [x] remove 
- [x] mark as done
- [x] do
- [x] re-do 
- [x] listAll

## Tests

```bash
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 55, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] 
```

- [x] Unit
- [x] Integration
- [x] Contract
- [x] Mutation
- [ ] Stress
- [ ] Chaos