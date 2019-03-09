namespace :query do
  task :create => :environment do
    p Word.create(text: 'そこに山があるから。', user_name: 'ジョージ・マロリー', background_image_url: 'https://yamabluesky.files.wordpress.com/2019/01/slide01-2.jpg', user_image_url: 'http://pbs.twimg.com/profile_images/1015937963140608000/jMjEbvdl_normal.jpg')
  end
  task :show => :environment do
    Word.where(text: 'test').each do |word|
      p word
    end
  end
  task :delete => :environment do
    @words = Word.all
    @words.each do |word|
      Word.where(text: word.text).delete()
    end
  end

  task :all => :environment do
    @words = Word.all
  end
end
