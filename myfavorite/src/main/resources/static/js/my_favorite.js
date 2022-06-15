$(function() {

	// 検索ボタン押下時の処理
	$(document).on('click', '#buttonS', function() {

		console.log("検索ボタンが押されたよ！");

		// $.ajax({オプション})
		$.ajax({
			// 何処に接続するか
			url: '/scarch',
			//リクエストの種類（POST）を指定
			type: 'POST',
			// サーバーとのやり取りを行うためのデータ形式
			dataType: 'text',
			// リクエストパラメータ
			data: {
				//入力された値を取得送信
				id: $('#id').val(),
				title: $('#title').val(),
				min: $('#min').val(),
				max: $('#max').val(),
				select: $('#select').val()
			}

		})
			.done(function(data) {
				// 通信成功
				// dataの中身がnullの場合
				if (!data) {
					// arletで報告
					alert("データが見つかりませんでした");
					return;
				}

				// テーブルに取得したデータを表示
				$('#table').find('tr:gt(0)').remove();
				const tableData = JSON.parse(data);
				let i = 0;
				for (i = 0; i < tableData.length; i++) {
					let trtag = $('<tr />');
					trtag.append($('<td></td>').text(tableData[i].id));
					trtag.append($('<td></td>').text(tableData[i].grouping));
					trtag.append($('<td></td>').text(tableData[i].title));
					trtag.append($('<td></td>').text(tableData[i].points));
					trtag.append($('<td style="text-align:left;white-space:normal;"></td>').text(tableData[i].memo));
					$('#table').append(trtag);
				}
			})
			.fail(function() {
				// 通信失敗
				// アラートで「通信に失敗しました。」と表示する
				alert('通信に失敗しました。');
			});
	});


	// 一覧表示ボタン押下時の処理
	$(document).on('click', '#buttonA', function() {

		console.log("一覧表示ボタンが押されたよ！");

		//$.ajax({オプション})
		$.ajax({
			// 何処に接続するか
			url: '/all',
			//リクエストの種類を指定「GETやPOST」
			type: 'POST',
			// サーバーとのやり取りを行うためのデータ形式
			dataType: 'text',
		})
			.done(function(data) {
				// 通信成功
				if (!data) {
					alert("データが見つかりませんでした");
					return;
				}
				// テーブルへ取得したデータを表示
				$('#table').find('tr:gt(0)').remove();
				const tableData = JSON.parse(data);
				let i = 0;
				for (i = 0; i < tableData.length; i++) {
					let trtag = $('<tr />');
					trtag.append($('<td></td>').text(tableData[i].id));
					trtag.append($('<td></td>').text(tableData[i].grouping));
					trtag.append($('<td></td>').text(tableData[i].title));
					trtag.append($('<td></td>').text(tableData[i].points));
					trtag.append($('<td></td>').text(tableData[i].memo));

					$('#table').append(trtag);
				}
			})
			.fail(function() {
				// 通信失敗
				// アラートで「通信に失敗しました。」と表示する
				alert('通信に失敗しました。');
			});
	});


	// クリアボタン押下時の処理
	$(document).on('click', '#buttonC', function() {

		console.log("クリアボタンが押されたよ！");

		// それぞれのテキストボックス等にnullを入れる
		$('#id').val(null);
		$('#title').val(null);
		$('#min').val(null);
		$('#max').val(null);
		$('#select').val(null);
		$('#memo').val(null);
		$('#points').val(null);
		$('#result').html(null);
		$('#resultTable').html(null);
	});

	// 登録画面ボタン押下時の処理
	$(document).on('click', '#buttonR', function() {

		console.log("登録画面遷移ボタンが押されたよ！");

		$.ajax({
			type: 'GET',
			url: '/html/add.html',
			datatype: 'html',
			success: function(data) {
				$('#body').html(data);
			},
			error: function() {
				alert("ざんねん");
			}


		});

	});

	// 戻るボタン押下時
	$(document).on('click', '#buttonB', function() {

		console.log("戻るボタンが押されたよ！");

		$.ajax({
			type: 'GET',
			url: '/html/my_favoriteDataList_copy.html',
			datatype: 'html',
			success: function(data) {
				$('#body').html(data);
			},
			error: function() {
				alert("ざんねん");
			}


		});

	});

	// 登録ボタン押下時
	$(document).on('click', '#buttonRr', function() {

		console.log("登録ボタンが押されたよ！");

		// $.ajax({オプション})
		$.ajax({
			// 何処に接続するか
			url: '/save',
			//リクエストの種類（POST）を指定
			type: 'POST',
			// サーバーとのやり取りを行うためのデータ形式
			dataType: 'text',
			// リクエストパラメータ
			data: {
				//入力された値を取得送信
				title: $('#title').val(),
				points: $('#points').val(),
				select: $('#select').val(),
				memo: $('#memo').val(),
			}
		})
			.done(function(data) {
				// 通信成功
				//dataの中身が100文字以上
				if (data.length >= 100) {
					$('#resultTable').html(data)
					$('#result').html("以上を登録しました")
				} else {
					$('#result').html(data)
					$('#resultTable').html(null)
				}
			})
			.fail(function() {
				// 通信失敗
				// アラートで「通信に失敗しました。」と表示する
				alert('通信に失敗しました。');
			});
	});


}); 