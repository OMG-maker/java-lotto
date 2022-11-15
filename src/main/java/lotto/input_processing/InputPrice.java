package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.*;

public class InputPrice {
    static UserInputException userInputException = new UserInputException();

    InputPrice(){}

    /** 구입금액 입력받기, 로또 구매 개수 계산 및 예외처리 후 로또 개수 반환 */
    private static int buyingLotto() {
        return countingLottoCounts(inputPurchasingAmount());
    }

    /** 구입금액 입력받기 기능 */
    private static int inputPurchasingAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        String purchasingAmount = Console.readLine();
        if (!userInputException.checkInputIsInteger(purchasingAmount)){
            throw new IllegalArgumentException("[ERROR]");
        }
        return Integer.parseInt(purchasingAmount);
    }
    /** 구입금액에 따른 로또 구매 개수 처리 기능 */
    private static int countingLottoCounts(int purchasingAmount) {
        if (!userInputException.checkMultipleOfThousand(purchasingAmount)){
            throw new IllegalArgumentException("[ERROR]");
        }
        // 구매한 로또 개수를 출력한다.
        System.out.println(purchasingAmount / 1000 + "개를 구매했습니다.");
        return purchasingAmount / 1000;
    }
    /** 각 6개의 서로 다른 로또 번호 랜덤 생성 기능 */
    static ArrayList<Lotto> createLottoNumbers(){
        int countOfLotto = buyingLotto();
        ArrayList<Lotto> lottoList = new ArrayList<Lotto>();
        for (int i=0;i<countOfLotto;i++){
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            Integer[] arr = numbers.toArray(new Integer[numbers.size()]);
            Arrays.sort(arr); // ApplicationTest 에서 List.of를 사용해 직접 List 를 정렬하면 오류가 발생한다.
            List<Integer> sortedList = Arrays.asList(arr); // List<Integer> 형태로 반환하기 위해 다시 형변환
            System.out.println(sortedList); // 로또 번호를 출력한다.
            lottoList.add(new Lotto(sortedList));
        }
        return lottoList;
    }
}